package com.codurance.training.tasks.usecase.port;

import tw.teddysoft.ezddd.core.usecase.StoreData;
import tw.teddysoft.ezddd.core.usecase.domainevent.DomainEventData;

import java.util.ArrayList;
import java.util.List;

public class CheckListDto implements StoreData {

    private String id;
    private final List<ProjectDto> projects;

    public CheckListDto(String id, List<ProjectDto> projects) {
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
        return new ArrayList<>();
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

    public List<ProjectDto> getProjects() {
        return projects;
    }

}
