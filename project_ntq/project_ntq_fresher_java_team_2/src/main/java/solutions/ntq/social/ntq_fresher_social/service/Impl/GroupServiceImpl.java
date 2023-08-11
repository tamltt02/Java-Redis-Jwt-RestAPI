package solutions.ntq.social.ntq_fresher_social.service.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import solutions.ntq.social.ntq_fresher_social.entity.Group;
import solutions.ntq.social.ntq_fresher_social.entity.User;
import solutions.ntq.social.ntq_fresher_social.entity.UserTeam;
import solutions.ntq.social.ntq_fresher_social.model.request.GroupRequest;
import solutions.ntq.social.ntq_fresher_social.repositories.GroupRepository;
import solutions.ntq.social.ntq_fresher_social.repositories.UserRepository;
import solutions.ntq.social.ntq_fresher_social.repositories.UserTeamRepositpry;
import solutions.ntq.social.ntq_fresher_social.service.GroupService;
import solutions.ntq.social.ntq_fresher_social.utils.RequestStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;
    private final UserTeamRepositpry userTeamRepositpry;

    @Override
    public ResponseEntity<?> getGroupById(Long id) {
        try {
            Group group = groupRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(group);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.NOT_FOUND.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> addUserGroup(Long idUser, Long idGroup) {
        try {
            UserTeam userTeam = groupRepository.findByUserAndGroup(idUser, idGroup);
            if (userTeam != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.ALLREADY_EXITS.getMessage());
            }
            User user = userRepository.findById(idUser).get();
            Group group = groupRepository.findById(idGroup).get();
            UserTeam userTeamNew = UserTeam.builder().user(user).group(group).build();
            userTeamRepositpry.save(userTeamNew);
            return ResponseEntity.status(HttpStatus.OK).body(userTeamNew);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.NOT_FOUND.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> createGroup(GroupRequest groupRequest) {
        Group group = modelMapper.map(groupRequest, Group.class);
        List<Group> list = groupRepository.findAll();
        for (Group groups : list) {
            if (group.getNameGroup().equals(groups.getNameGroup())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.EXIST_GROUP.getMessage());
            } else {
                if (group != null) {
                    groupRepository.save(group);
                    return ResponseEntity.status(HttpStatus.OK.value()).body(group);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.NOT_FOUND.getMessage());
    }
}
