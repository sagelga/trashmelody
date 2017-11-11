package com.trashmelody.tests.beatmap;

import lt.ekgame.beatmap_analyzer.beatmap.Beatmap;
import lt.ekgame.beatmap_analyzer.beatmap.mania.ManiaBeatmap;
import lt.ekgame.beatmap_analyzer.difficulty.Difficulty;
import lt.ekgame.beatmap_analyzer.difficulty.ManiaDifficulty;
import lt.ekgame.beatmap_analyzer.parser.BeatmapException;
import lt.ekgame.beatmap_analyzer.parser.BeatmapParser;
import lt.ekgame.beatmap_analyzer.performance.Performance;
import lt.ekgame.beatmap_analyzer.performance.scores.Score;
import lt.ekgame.beatmap_analyzer.utils.Mods;

import java.io.File;
import java.io.FileNotFoundException;

public class TestMania {
    public static void main(String[] args) {
        BeatmapParser parser = new BeatmapParser();
        //File file = new File("C:\\Program Files\\osu!\\Songs\\341207  - VSRG Pattern Training\\5min (iJinjin) [test2].osu");
        File file = new File("/Users/kavinvin/Repositories/trashmelody/core/assets/songs/Hitorigoto/ClariS - Hitorigoto -TV MIX- (Doormat) [Easy].osu");
        ManiaBeatmap beatmap = null;//.withMods(new Mods(Mod.DOUBLE_TIME));
        try {
            beatmap = parser.parse(file, ManiaBeatmap.class);
        } catch (BeatmapException | FileNotFoundException e) {
            e.printStackTrace();
        }

        if (beatmap != null) {
            beatmap.getHitObjects().forEach(System.out::println);
        }

//        ManiaDifficulty diff = beatmap.getDifficulty();
//        System.out.println("stars: " + beatmap.getDifficulty().getStars());
//
//        Score score = Score.of(beatmap).score(978993).maniaAccuracy(39, 2, 1, 2).build();
//        Performance perf = diff.getPerformance(score);
//        System.out.println("\nacc: " + perf.getAccuracy());
//        System.out.println("pp: " + perf.getPerformance());
//        System.out.println("strain: " + perf.getSpeedPerformance());
//        System.out.println("acc: " + perf.getAccuracyPerformance());
//
//        Difficulty diffReal = new ManiaDifficulty(beatmap, Mods.NOMOD, 7.949989318847656, null);
//        Performance perf2 = diffReal.getPerformance(score);
//        System.out.println("\nacc: " + perf2.getAccuracy());
//        System.out.println("pp: " + perf2.getPerformance());
//        System.out.println("strain: " + perf2.getSpeedPerformance());
//        System.out.println("acc: " + perf2.getAccuracyPerformance());
    }
}
