package io.github.halochn.plugins

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils

class HaloTransform : Transform() {
    override fun getName(): String {
        return "HaloCHN"
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun isIncremental(): Boolean {
        return false
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun transform(transformInvocation: TransformInvocation?) {
//        super.transform(transformInvocation)
        transformInvocation?.let { transformInvocation ->
            val outputProvider = transformInvocation.outputProvider
            transformInvocation.inputs.forEach {
                it.jarInputs.forEach { jar ->
                    val dest = outputProvider.getContentLocation(
                        jar.name,
                        jar.contentTypes,
                        jar.scopes,
                        Format.JAR
                    )
                    FileUtils.copyFile(jar.file, dest)
                }
                it.directoryInputs.forEach { dir ->
                    val dest = outputProvider.getContentLocation(
                        dir.name,
                        dir.contentTypes,
                        dir.scopes,
                        Format.DIRECTORY
                    )
                    FileUtils.copyDirectory(dir.file, dest)
                }
            }
        }
    }
}