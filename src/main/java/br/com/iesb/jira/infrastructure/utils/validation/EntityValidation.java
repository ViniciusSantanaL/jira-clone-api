package br.com.iesb.jira.infrastructure.utils.validation;

import br.com.iesb.jira.infrastructure.exception.BusinessException;

import java.util.*;
import java.util.stream.Collectors;

public class EntityValidation {

    public static <T> void validateIfEntityExist(final List<UUID> externalIds,
                                            final List<T> entityIds, final String enityName) {

        Set<UUID> userIds = entityIds.stream()
                .map(entity -> ((BaseValidation) entity).getId())
                .collect(Collectors.toSet());
        Set<UUID> entityNotExist = new HashSet<>(externalIds);

        entityNotExist.removeAll(userIds);

        if(!entityNotExist.isEmpty()) {
            String baseMessage = String.format("Those %s_ids Not Exist: [",enityName);
            StringJoiner message = new StringJoiner(",", baseMessage, "]");

            entityNotExist.forEach(voId -> message.add(voId.toString()));

            throw new BusinessException(message.toString());
        }
    }
}
