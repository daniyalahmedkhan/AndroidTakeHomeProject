#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_daniyalxdubizzle_androidtakehomeproject_utilities_Constants_getBaseUAT(JNIEnv *env, jobject thiz) {
    return (*env) -> NewStringUTF(env, "aHR0cHM6Ly9leTNmMnkwbnJlLmV4ZWN1dGUtYXBpLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tL2RlZmF1bHQv");
}