package com.trashmelody.tests.beatmap;

import lt.ekgame.beatmap_analyzer.difficulty.OsuDifficulty;
import lt.ekgame.beatmap_analyzer.parser.BeatmapException;
import lt.ekgame.beatmap_analyzer.parser.BeatmapParser;
import lt.ekgame.beatmap_analyzer.performance.Performance;
import lt.ekgame.beatmap_analyzer.performance.scores.Score;
import lt.ekgame.beatmap_analyzer.utils.Mod;
import lt.ekgame.beatmap_analyzer.utils.Mods;

import java.io.File;
import java.io.FileNotFoundException;

public class OsuBeatmap {
    public static void main(String[] args) {
        BeatmapParser parser = new BeatmapParser();
        lt.ekgame.beatmap_analyzer.beatmap.osu.OsuBeatmap beatmap = null;
        try {
            beatmap = parser.parse(new File("/Users/kavinvin/Repositories/trashmelody/core/assets/songs/Hitorigoto/ClariS - Hitorigoto -TV MIX- (Doormat) [Easy].osu"), lt.ekgame.beatmap_analyzer.beatmap.osu.OsuBeatmap.class);
        } catch (BeatmapException | FileNotFoundException e) {
            e.printStackTrace();
        }
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
