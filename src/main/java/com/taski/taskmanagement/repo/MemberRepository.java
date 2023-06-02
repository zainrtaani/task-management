package com.taski.taskmanagement.repo;


import com.taski.taskmanagement.entity.user.Member;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    @NotNull Optional<Member> findById(@NotNull String id);

    List<Member> findAllByLeaderId(@NotNull String leaderId);
}
