package br.com.iesb.jira.domain.team.vo;

import br.com.iesb.jira.domain.project.vo.ProjectVO;
import br.com.iesb.jira.domain.user.vo.UserVO;

import java.util.Set;
import java.util.UUID;

public record TeamVO(UUID id, String teamName, Set<UserVO> users, Set<ProjectVO> projects) {
    public TeamVO(String teamName, Set<UserVO> users) {
        this(null, teamName, users, null);
    }
}
