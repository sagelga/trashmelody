package com.trashmelody.beatmap.test;

import com.trashmelody.beatmap.parser.beatmap.mania.ManiaBeatmap;
import com.trashmelody.beatmap.parser.parser.BeatmapException;
import com.trashmelody.beatmap.parser.parser.BeatmapParser;

import java.io.File;
import java.io.FileNotFoundException;

import static io.vavr.API.println;

public class TestMania {
    public static void main(String[] args) {
        BeatmapParser parser = new BeatmapParser();
        //File file = new File("C:\\Program Files\\osu!\\Songs\\341207  - VSRG Pattern Training\\5min (iJinjin) [test2].osu");
        File file = new File("/Users/zartre/Documents/Git/trashmelody/core/assets/songs/Hitorigoto/ClariS - Hitorigoto -TV MIX- (Doormat) [Easy].osu");
        ManiaBeatmap beatmap = null;//.withMods(new Mods(Mod.DOUBLE_TIME));
        try {
            beatmap = parser.parse(file.toPath(), ManiaBeatmap.class);
        } catch (BeatmapException | FileNotFoundException e) {
            e.printStackTrace();
        }

        if (beatmap != null) {
            beatmap.getHitObjects().forEach(System.out::println);
            println(String.format("HitObjectEntity count: %d", beatmap.getHitObjects().size()));
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
