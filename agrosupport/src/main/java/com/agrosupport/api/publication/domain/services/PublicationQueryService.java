package com.agrosupport.api.publication.domain.services;

import com.agrosupport.api.publication.domain.model.aggregates.Publication;
import com.agrosupport.api.publication.domain.model.queries.GetAllPublicationsQuery;
import com.agrosupport.api.publication.domain.model.queries.GetPublicationByAdvisorIdQuery;
import com.agrosupport.api.publication.domain.model.queries.GetPublicationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PublicationQueryService {
    List<Publication> handle(GetAllPublicationsQuery query);
    Optional<Publication> handle(GetPublicationByIdQuery query);
    List<Publication> handle(GetPublicationByAdvisorIdQuery query);
}
