package com.codurance.training.tasks.usecase.port;

import tw.teddysoft.ezddd.core.usecase.StoreData;
import tw.teddysoft.ezddd.core.usecase.domainevent.DomainEventData;

import java.util.List;

public class CheckListPo implements StoreData {

    private String id;
    private final List<ProjectPo> projects;

    public CheckListPo(String id, List<ProjectPo> projects) {
        this.id = id;
        this.projects = projects;
    }

    @Override
    public long getVersion() {
        return 0;
    }

    @Override
    public void setVersion(long version) {

    }

    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public List<DomainEventData> getDomainEventDatas() {
        return null;
    }

    @Override
    public void setDomainEventDatas(List<DomainEventData> domainEventDatas) {

    }

    @Override
    public String getStreamName() {
        return null;
    }

    @Override
    public void setStreamName(String streamName) {

    }

    public List<ProjectPo> getProjects() {
        return projects;
    }

}
