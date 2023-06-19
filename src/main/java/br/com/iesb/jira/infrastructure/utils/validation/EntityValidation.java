package br.com.iesb.jira.infrastructure.utils.validation;

import br.com.iesb.jira.infrastructure.exception.BusinessException;

import java.util.*;
import java.util.stream.Collectors;

public class EntityValidation {

    public static void validateIfEntityExist(final List<UUID> externalIds,
                                            final Set<UUID> entityIds, final String enityName) {

        Set<UUID> entityNotExist = new HashSet<>(externalIds);

        entityNotExist.removeAll(entityIds);

        if(!entityNotExist.isEmpty()) {
            String baseMessage = String.format("Those %s_ids Not Exist: [",enityName);
            StringJoiner message = new StringJoiner(",", baseMessage, "]");

            entityNotExist.forEach(voId -> message.add(voId.toString()));

            throw new BusinessException(message.toString());
        }
    }
}
