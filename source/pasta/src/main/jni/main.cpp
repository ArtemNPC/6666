#include <pthread.h>
#include <jni.h>
#include <Includes/Utils.h>

extern "C" {

/*Time For Bools And all*/

const char *libName = "libil2cpp.so";

JNIEXPORT jobjectArray  JNICALL Java_il2cpp_Main_getFeatures(JNIEnv *env, jobject activityObject) {
	jobjectArray ret;
	// switch_featureid_text
	const char *features[] = {
		"switch_1_Radar",
		"slider_2_FOV_300_0",
	};
	int Total_Feature = (sizeof features /
						 sizeof features[0]); //Now you dont have to manually update the number everytime;
	
	ret = (jobjectArray) env->NewObjectArray(Total_Feature, env->FindClass("java/lang/String"), env->NewStringUTF(""));
	int i;
	for (i = 0; i < Total_Feature; i++)
		env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));
	return (ret);
}

JNIEXPORT void JNICALL
Java_il2cpp_Main_Changes(JNIEnv *env,jobject activityObject,jint feature,jint value) {
	/*  FEATURES  */
	switch (feature) {
		
	}
}

// EXTERN END
}

// ---------- Hooking ---------- //

void *hack_thread(void *) {
	
	
	
    // ---------- Hook ---------- //
	
    return NULL;
}

JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *globalEnv;
    vm->GetEnv((void **) &globalEnv, JNI_VERSION_1_6);

    // Create a new thread so it does not block the main thread, means the game would not freeze
    pthread_t ptid;
    pthread_create(&ptid, NULL, hack_thread, NULL);

    return JNI_VERSION_1_6;
}

JNIEXPORT void JNICALL
JNI_OnUnload(JavaVM *vm, void *reserved) {}
