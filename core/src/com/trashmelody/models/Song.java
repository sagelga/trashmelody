package com.trashmelody.models;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Song {

    private static final PathMatcher osuExtension = FileSystems.getDefault().getPathMatcher("glob:**/*.osu");

    private Path path;

    public Song(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

//    public Stream<Difficulty> getDifficulties() {
//        return listFiles
//                .apply(path)
//                .get()
//                .filter(osuExtension::matches)
//                .map(Difficulty::new);
//    }

}
