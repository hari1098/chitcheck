package com.chitcheck.defaulters.group_details;

import com.chitcheck.defaulters.personal_details.PersonalDetails;
import com.chitcheck.defaulters.personal_details.PersonalDetailsRepository;
import com.chitcheck.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupDetailsService {

    private final GroupDetailsRepository groupDetailsRepository;
    private final PersonalDetailsRepository personalDetailsRepository;

    public GroupDetailsService(GroupDetailsRepository groupDetailsRepository, PersonalDetailsRepository personalDetailsRepository) {
        this.groupDetailsRepository = groupDetailsRepository;
        this.personalDetailsRepository = personalDetailsRepository;
    }

    public GroupDetails createGroupDetails(GroupDetailsDto dto) {
        PersonalDetails personalDetails = personalDetailsRepository.findById(dto.getPersonalDetailsId())
                .orElseThrow(() -> new ResourceNotFoundException("Personal Details not found with id: " + dto.getPersonalDetailsId()));

        GroupDetails groupDetails = new GroupDetails();
        mapDtoToEntity(dto, groupDetails);
        groupDetails.setPersonalDetails(personalDetails);
        return groupDetailsRepository.save(groupDetails);
    }

    public List<GroupDetails> getAllGroupDetails() {
        return groupDetailsRepository.findAll();
    }

    public GroupDetails getGroupDetailsById(Integer id) {
        return groupDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group Details not found with id: " + id));
    }

    public GroupDetails updateGroupDetails(Integer id, GroupDetailsDto dto) {
        GroupDetails existingGroup = getGroupDetailsById(id);
        mapDtoToEntity(dto, existingGroup);
        return groupDetailsRepository.save(existingGroup);
    }

    public void deleteGroupDetails(Integer id) {
        if (!groupDetailsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Group Details not found with id: " + id);
        }
        groupDetailsRepository.deleteById(id);
    }

    private void mapDtoToEntity(GroupDetailsDto dto, GroupDetails entity) {
        entity.setGroupNo(dto.getGroupNo());
        entity.setPsoNumber(dto.getPsoNumber());
        entity.setAgreementNo(dto.getAgreementNo());
        entity.setAgreementDate(dto.getAgreementDate());
        entity.setInstallment(dto.getInstallment());
        entity.setChitValue(dto.getChitValue());
        entity.setFirstAuction(dto.getFirstAuction());
        entity.setLastAuction(dto.getLastAuction());
        entity.setAmountPaid(dto.getAmountPaid());
        entity.setAmountOutstanding(dto.getAmountOutstanding());
        entity.setBranchName(dto.getBranchName());
    }
}