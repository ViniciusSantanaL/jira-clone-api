package br.com.iesb.jira.domain.team.vo.builder;

import br.com.iesb.jira.domain.team.model.Team;
import br.com.iesb.jira.domain.team.vo.TeamVO;
import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.domain.user.vo.UserVO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TeamBuilder {

    private TeamBuilder() {
        throw new UnsupportedOperationException("cannot be call constructor");
    }

    public static Team create(TeamVO teamVO, List<User> users) {
        Team team = new Team();
        team.setTeamName(teamVO.teamName());
        team.setUsers(new HashSet<>(users));
        return team;
    }

    public static TeamVO createTeamVO(Team team) {
        Set<UserVO> usersVO = team.getUsers().stream()
                .map(u -> new UserVO(u.getId(), u.getUsername(), u.getUserEmail()))
                .collect(Collectors.toSet());
        return new TeamVO(team.getId(),team.getTeamName(), usersVO);
    }
}
