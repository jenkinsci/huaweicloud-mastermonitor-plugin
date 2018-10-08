package com.huawei.devcloud.monitor.model;

import hudson.model.*;
import jenkins.model.Jenkins;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;

import java.util.*;

public abstract class JenkinsInfo {
    protected transient Jenkins jenkins ;

    public JenkinsInfo(Jenkins jenkins ){
        this.jenkins = jenkins;
    }

    public abstract JobQueue getJobQueue(Integer totalJobs, Integer runningJobs, Integer queueJobs);

    public JobQueue getJobQueue(){
        Integer totalJobs = 0;
        Integer pipelineJobsCount = 0;
        Integer otherJobsCount = 0;
        Integer pendingJobs = 0;
        List<WorkflowJob> workflowJobs = NoAuthGetAllItems(WorkflowJob.class);
        List<AbstractProject> abstractProjectList =NoAuthGetAllItems(AbstractProject.class);
        Integer runningJobs = 0;
        List<hudson.model.Queue.Item> queueItemList = jenkins.getQueue().getUnblockedItems();
        pipelineJobsCount = workflowJobs.size();
        otherJobsCount = abstractProjectList.size();
        pendingJobs = queueItemList.size();
        Integer runningPipelineJobs = 0;
        Integer runningNormalJobs = 0;
        for (int j = 0; j < pipelineJobsCount; j ++){
            if(workflowJobs.get(j).isBuilding()){
                runningPipelineJobs++;
            }
        }
        for (int k = 0; k <otherJobsCount; k ++){
            if(abstractProjectList.get(k).isBuilding()){
                runningNormalJobs++;
            }
        }
        runningJobs = runningNormalJobs+ runningPipelineJobs;
        return getJobQueue(totalJobs, runningJobs, pendingJobs);
    }

    protected <T extends Item> List<T> NoAuthGetAllItems(Class<T> type) {
        List<T> r = new ArrayList<T>();
        NoAuthGetAllItems(Jenkins.getInstance(), type, r);
        return r;
    }

    protected static <T extends Item> void NoAuthGetAllItems(ItemGroup root, Class<T> type, List<T> r) {
        Map<String, TopLevelItem> itemMap = ((Jenkins)root).getItemMap();
        List<Item> items = new ArrayList<Item>(itemMap.values());
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return name(i1).compareToIgnoreCase(name(i2));
            }
            String name(Item i) {
                String n = i.getDisplayName();
                if (i instanceof ItemGroup) {
                    n += '/';
                }
                return n;
            }
        });
        for (Item i : items) {
            if (type.isInstance(i)) {
                r.add(type.cast(i));
            }
        }
    }

    public Map<String, Set<String>> getSlaveLabelMap() {
        Map<String, Set<String>> slaveLabelMap = new HashMap<String, Set<String>>();
        slaveLabelMap.put("pertmanent", getPermanentSlaveLabelSet());
        return slaveLabelMap;
    }

    private Set<String> getPermanentSlaveLabelSet() {
        Set<String> slaveLabelSet = new HashSet<String>();
        for (Node slave : Jenkins.getInstance().getNodes()) {
            slaveLabelSet.add(slave.getLabelString());
        }
        slaveLabelSet.add("master");
        return slaveLabelSet;
    }

}
