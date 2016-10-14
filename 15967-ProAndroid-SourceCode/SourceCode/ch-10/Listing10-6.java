// filename: OpenGLTestHarnessActivity.java
public class OpenGLTestHarnessActivity extends Activity 
{
   private OpenGLTestHarness mTestHarness;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      mTestHarness = new OpenGLTestHarness(this);
      mTestHarness.setRenderer(new SimpleTriangleRenderer(this));
      setContentView(mTestHarness);
   }

   @Override
   protected void onResume() {
   super.onResume();
   mTestHarness.onResume();
   }

   @Override
   protected void onPause() {
      super.onPause();
      mTestHarness.onPause();
   }
}


