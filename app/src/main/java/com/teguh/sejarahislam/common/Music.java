package com.teguh.sejarahislam.common;


import android.media.MediaPlayer;

import com.teguh.sejarahislam.R;

public class Music {

	public static boolean OFF = false;

	public static void playCorrent() {
		if (!OFF) {
			MediaPlayer mp = MediaPlayer.create(Shared.context, R.raw.correct_answer);
			mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.reset();
					mp.release();
					mp = null;
				}

			});
			mp.start();
		}
	}

	public static void showStar() {
		if (!OFF) {
			MediaPlayer mp = MediaPlayer.create(Shared.context, R.raw.star);
			mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.reset();
					mp.release();
					mp = null;
				}

			});
			mp.start();
		}
	}

	public static void wrongAnswer() {
		if (!OFF) {
			MediaPlayer mp = MediaPlayer.create(Shared.context, R.raw.wrong_answer);
			mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.reset();
					mp.release();
					mp = null;
				}

			});
			mp.start();
		}
	}
	public static void gameOver() {
		if (!OFF) {
			MediaPlayer mp = MediaPlayer.create(Shared.context, R.raw.game_over);
			mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.reset();
					mp.release();
					mp = null;
				}

			});
			mp.start();
		}
	}
	public static void gameOverPaused() {
		if (!OFF) {
			MediaPlayer mp = MediaPlayer.create(Shared.context, R.raw.game_over);
			mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.reset();
					mp.release();
					mp = null;
				}

			});
			mp.pause();
		}
	}

	public static void clickText() {
		if (!OFF) {
			MediaPlayer mp = MediaPlayer.create(Shared.context, R.raw.interface_click_mixkit);
			mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.reset();
					mp.release();
					mp = null;
				}

			});
			mp.start();
		}
	}
}
