package com.example.prj2be.service;

import com.example.prj2be.domain.Board;
import com.example.prj2be.domain.Member;
import com.example.prj2be.mapper.BoardMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {


   private final BoardMapper mapper;

   public boolean save(Board board) {

      return mapper.insert(board) == 1;
   }

   public boolean validate(Board board) {
      if (board == null) {
         return false;
      }

      if (board.getContent() == null || board.getContent().isBlank()) {
         return false;
      }

      if (board.getTitle() == null || board.getTitle().isBlank()) {
         return false;
      }

      if (board.getWriter() == null || board.getWriter().isBlank()) {
         return false;
      }

      return true;
   }

   public List<Board> list() {
      return mapper.selectAll();
   }

   public Board get(Integer id) {
      return mapper.selectById(id);
   }

   public boolean remove(Integer id) {
      return mapper.deleteById(id) == 1;
   }

   public boolean update(Board board) {
      return mapper.update(board) == 1;

   }
}