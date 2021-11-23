package com.mediscreen.notes.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    Note mapToEntity(NoteDto noteDto);
    NoteDto mapToDTO(Note note);
    List<NoteDto> mapToDtoList (List<Note> noteList);
}
