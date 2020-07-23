package io.github.halochn.plugins;

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class HaloCHN : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val extension =
                extensions.create("halochn", HaloCHNExtension::class.java)
            afterEvaluate {
                extension.name?.let {
                    println("Hello ${extension.name}")
                }
            }

            with(project.extensions.getByType(BaseExtension::class.java)) {
                registerTransform(HaloTransform())
            }
        }
    }
}