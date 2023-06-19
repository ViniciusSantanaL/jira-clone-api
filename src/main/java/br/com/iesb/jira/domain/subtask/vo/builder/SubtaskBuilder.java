package br.com.iesb.jira.domain.subtask.vo.builder;

import br.com.iesb.jira.domain.subtask.model.Subtask;
import br.com.iesb.jira.domain.subtask.model.SubtaskStatus;
import br.com.iesb.jira.domain.subtask.vo.SubtaskVO;
import br.com.iesb.jira.domain.task.model.Task;

public class SubtaskBuilder {
    private SubtaskBuilder() {
        throw new UnsupportedOperationException("cannot be call constructor");
    }

    public static Subtask create(SubtaskVO subtaskVO, Task task) {
        Subtask subtask = new Subtask();
        subtask.setSubtaskStatus(subtaskVO.subtaskStatus() != null ? subtaskVO.subtaskStatus() : SubtaskStatus.TO_DO);
        subtask.setSubtaskTitle(subtaskVO.subtaskTitle());
        subtask.setTask(task);
        return subtask;
    }

    public static SubtaskVO createSubtaskVO(Subtask subtask) {
        return new SubtaskVO(subtask.getId(),
                subtask.getSubtaskTitle(),
                subtask.getTask().getId(),
                subtask.getSubtaskStatus());
    }
}
