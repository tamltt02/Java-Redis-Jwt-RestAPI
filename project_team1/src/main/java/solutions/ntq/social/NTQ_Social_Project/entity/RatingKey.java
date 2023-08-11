//package solutions.ntq.social.NTQ_Social_Project.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//@Data
//@Embeddable
//@AllArgsConstructor
//@NoArgsConstructor
//public class RatingKey implements Serializable {
//    @Column(name = "user_id")
//    Long userId;
//
//    @Column(name = "group_id")
//    Long groupId;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        RatingKey ratingKey = (RatingKey) o;
//        return Objects.equals(userId, ratingKey.userId) && Objects.equals(groupId, ratingKey.groupId);
//    }
//
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, groupId);
//    }
//}
