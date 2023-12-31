package com.example.prj2be.mapper.ds;

import com.example.prj2be.domain.ds.DsComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DsCommentMapper {

    @Insert("""
            INSERT INTO businesscomment (businessId, memberId, comment)
            VALUES (#{businessId}, #{memberId}, #{comment})
            """)
    int insert(DsComment comment);

    @Select("""
            SELECT bc.id, bc.businessId, bc.memberId, bc.comment, bc.inserted, m.nickName `memberNickName`
            FROM businesscomment bc
                JOIN member m
                    ON bc.memberId = m.id
            WHERE businessId = #{id}
            ORDER By id DESC
            LIMIT #{from}, 5
            """)
    List<DsComment> selectByBusinessId(Integer from, Integer id);

    @Select("""
            SELECT * FROM businesscomment
            WHERE id = #{id}
            """)
    DsComment selectById(Integer id);

    @Update("""
            UPDATE businesscomment
            SET comment = #{comment}
            WHERE id = #{id}
            """)
    int update(DsComment comment);

    @Delete("""
            DELETE FROM businesscomment
            WHERE id = #{id}
            """)
    int deleteById(Integer id);

    @Select("""
            SELECT COUNT(*)
            FROM businesscomment
            WHERE businessId = #{id}
            """)
    int findById(Integer id);

    @Select("""
            SELECT * FROM businesscomment
            WHERE memberId = #{memberId}
            """)
    List<DsComment> selectByMemberId(String memberId);

    @Select("""
            SELECT id FROM business
            WHERE name = #{name}
            """)
    Integer selectIdByName(String name);

    @Select("""
            SELECT COUNT(*) FROM businesscomment
            WHERE businessId = #{id}
            """)
    int countAll(Integer id);
}
