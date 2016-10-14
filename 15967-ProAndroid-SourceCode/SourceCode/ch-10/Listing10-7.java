// filename: OpenGLTestHarness.java
public class OpenGLTestHarness extends SurfaceView
implements SurfaceHolder.Callback
{
   public static final Semaphore sEglSemaphore = new Semaphore(1);
   public boolean mSizeChanged = true;
   public SurfaceHolder mHolder;
   private OpenGLDrawingThread mGLThread;

   public OpenGLTestHarness(Context context) {
      super(context);
      init();
   }

   public OpenGLTestHarness(Context context, AttributeSet attrs) {
      super(context, attrs);
      init();
   }

   private void init() {
      mHolder = getHolder();
      mHolder.addCallback(this);
      mHolder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
   }

   public SurfaceHolder getSurfaceHolder() {
      return mHolder;
   }

   public void setRenderer(Renderer renderer) {
      mGLThread = new OpenGLDrawingThread(this,renderer);
      mGLThread.start();
   }

   public void surfaceCreated(SurfaceHolder holder) {
      mGLThread.surfaceCreated();
   }

   public void surfaceDestroyed(SurfaceHolder holder) {
      mGLThread.surfaceDestroyed();
   }

   public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
      mGLThread.onWindowResize(w, h);
   }

   public void onPause() {
      mGLThread.onPause();
   }

   public void onResume() {
      mGLThread.onResume();
   }

   @Override public void onWindowFocusChanged(boolean hasFocus) {
      super.onWindowFocusChanged(hasFocus);
      mGLThread.onWindowFocusChanged(hasFocus);
   }

   @Override
   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      mGLThread.requestExitAndWait();
   }
}


