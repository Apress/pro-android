//Ask for an implementation of EGL10
EGL10 mEgl = (EGL10) EGLContext.getEGL();

//get the default display
EGLDisplay mEglDisplay = mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);

//initialize the display
int[] version = new int[2];
mEgl.eglInitialize(mEglDisplay, version);

//config spec
int[] configSpec = {
   EGL10.EGL_DEPTH_SIZE, 0,
   EGL10.EGL_NONE
};

EGLConfig[] configs = new EGLConfig[1];
int[] num_config = new int[1];

mEgl.eglChooseConfig(mEglDisplay, configSpec, configs, 1,
                  num_config);
mEglConfig = configs[0];

//Create EGL Context
mEglContext = mEgl.eglCreateContext(mEglDisplay, mEglConfig,
EGL10.EGL_NO_CONTEXT, null);


