package ljh.gold.community.service;

import ljh.gold.community.dto.NotificationDTO;
import ljh.gold.community.dto.PaginationDOT;
import ljh.gold.community.dto.QuestionDTO;
import ljh.gold.community.enums.NotificationStatusEnum;
import ljh.gold.community.enums.NotificationTypeEnum;
import ljh.gold.community.exception.CustomizeErrorCode;
import ljh.gold.community.exception.CustomizeException;
import ljh.gold.community.mapper.NotificationMapper;
import ljh.gold.community.mapper.UserMapper;
import ljh.gold.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/10 11:34
 */
@Service
public class NotificationService {
    @Autowired(required = false)
    private NotificationMapper notificationMapper;
    @Autowired(required = false)
    private UserMapper userMapper;

    public PaginationDOT list(Long account_id, Integer page, Integer size) {
        PaginationDOT<NotificationDTO> paginationDOT = new PaginationDOT<>();
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(account_id);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);
        Integer totalPage = 0;
        Integer offset = 0;
        if (totalCount != 0) {
            if (totalCount % size == 0) {
                totalPage = totalCount / size;
            } else {
                totalPage = totalCount / size + 1;
            }
            if (page < 1) {
                page = 1;
            }
            if (page > totalPage) {
                page = totalPage;
            }
            offset = size * (page - 1);
        }

        NotificationExample notificationExample1 = new NotificationExample();
        notificationExample1.createCriteria().andReceiverEqualTo(account_id);
        notificationExample1.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(notificationExample1, new RowBounds(offset, size));
        if (notifications.size() == 0) {
            return paginationDOT;
        }
        List<NotificationDTO> notificationDTOs = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOs.add(notificationDTO);
        }
        paginationDOT.setData(notificationDTOs);
        paginationDOT.setPagination(page, totalPage);
        return paginationDOT;
    }

    public Long unReadCount(Long account_id) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(account_id).andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {

        Notification notification = notificationMapper.selectByPrimaryKey(id);
         if (notification==null){
             throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!notification.getReceiver().equals(user.getAccount_id())){
                throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAILED);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        NotificationDTO notificationDTO=new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;

    }
}
