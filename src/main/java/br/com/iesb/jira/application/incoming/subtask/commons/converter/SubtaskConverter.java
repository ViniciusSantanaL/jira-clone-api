package br.com.iesb.jira.application.incoming.subtask.commons.converter;

import br.com.iesb.jira.application.incoming.subtask.commons.request.SubtaskRequest;
import br.com.iesb.jira.application.incoming.subtask.commons.response.SubtaskResponse;
import br.com.iesb.jira.domain.subtask.model.Subtask;
import br.com.iesb.jira.domain.subtask.vo.SubtaskVO;

import java.util.UUID;

public class SubtaskConverter {

    private SubtaskConverter() {
        throw new UnsupportedOperationException("can not be call constructor");
    }

    public static SubtaskVO of(final SubtaskRequest request) {
        return new SubtaskVO(null, request.subtaskTitle(), request.taskId(), request.subtaskStatus());
    }

    public static SubtaskVO of(final UUID commentId, final SubtaskRequest request) {
        return new SubtaskVO(commentId, request.subtaskTitle(), request.taskId(), request.subtaskStatus());
    }

    public static SubtaskResponse toResponse(SubtaskVO subtaskVO) {
        return new SubtaskResponse(subtaskVO.subtaskId(),
                subtaskVO.subtaskTitle(),
                subtaskVO.subtaskId(),
                subtaskVO.subtaskStatus());
    }

    public static SubtaskResponse toResponse(Subtask subtask) {
        return new SubtaskResponse(subtask.getId(),
                subtask.getSubtaskTitle(),
                subtask.getTask().getId(),
                subtask.getSubtaskStatus());
    }
}
