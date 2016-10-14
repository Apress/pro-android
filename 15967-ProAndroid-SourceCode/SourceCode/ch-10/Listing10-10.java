//filename: Renderer.java
public interface Renderer
{
   int[] getConfigSpec();
   void surfaceCreated(GL10 gl);
   void sizeChanged(GL10 gl, int width, int height);
   void drawFrame(GL10 gl);
}


