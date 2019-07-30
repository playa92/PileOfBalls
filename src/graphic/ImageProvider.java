package graphic;

import java.awt.Image;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class ImageProvider {

		private static Image paused;

		static {
			try {
				setPaused((ImageIO.read(new File("images/paused.png"))));
		
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	
		public static Image getPaused() {
			return paused;
		}

		public static void setPaused(Image paused) {
			ImageProvider.paused = paused;
		}
	}

	