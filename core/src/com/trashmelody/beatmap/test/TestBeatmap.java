package com.trashmelody.beatmap.test;

import com.trashmelody.beatmap.parser.beatmap.osu.OsuBeatmap;
import com.trashmelody.beatmap.parser.difficulty.OsuDifficulty;
import com.trashmelody.beatmap.parser.parser.BeatmapException;
import com.trashmelody.beatmap.parser.parser.BeatmapParser;
import com.trashmelody.beatmap.parser.performance.Performance;
import com.trashmelody.beatmap.parser.performance.scores.Score;
import com.trashmelody.beatmap.parser.utils.Mod;
import com.trashmelody.beatmap.parser.utils.Mods;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class TestBeatmap {
    public static void main(String[] args) {
        BeatmapParser parser = new BeatmapParser();
        OsuBeatmap beatmap = null;
        try {
            beatmap = parser.parse(Paths.get("/Users/kavinvin/Repositories/trashmelody/core/assets/songs/Hitorigoto/ClariS - Hitorigoto -TV MIX- (Doormat) [Easy].osu"), OsuBeatmap.class);
        } catch (BeatmapException | FileNotFoundException e) {
            e.printStackTrace();
        }

        beatmap.getHitObjects().forEach(System.out::println);

        System.out.println(beatmap.getMaxCombo());

        OsuDifficulty diff = beatmap.getDifficulty(new Mods(Mod.HARDROCK));
        System.out.println("stars: " + diff.getStars());
        System.out.println("aim:   " + diff.getAim());
        System.out.println("speed: " + diff.getSpeed());

        Score score = Score.of(beatmap).combo(2358).accuracy(0.9971, 1).build();

        Performance perf = diff.getPerformance(score);
        System.out.println("\nacc:      " + perf.getAccuracy());
        System.out.println("aim_pp:   " + perf.getAimPerformance());
        System.out.println("speed_pp: " + perf.getSpeedPerformance());
        System.out.println("acc_pp:   " + perf.getAccuracyPerformance());
        System.out.println("total_pp: " + perf.getPerformance());
    }
}
