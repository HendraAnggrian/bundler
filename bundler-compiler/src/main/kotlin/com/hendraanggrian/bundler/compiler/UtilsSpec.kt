package com.hendraanggrian.bundler.compiler

import android.support.annotation.NonNull
import android.support.annotation.Nullable
import com.squareup.javapoet.*
import java.util.*
import javax.lang.model.element.Modifier.*

internal class UtilsSpec : Spec() {

    override val typeSpec: TypeSpec.Builder
    private val methodSpecGet: MethodSpec.Builder
    private val methodSpecPut: MethodSpec.Builder

    init {
        val generic = TypeVariableName.get("T")
        val parameterSpecs = Arrays.asList<ParameterSpec>(
                ParameterSpec.builder(TYPE_BUNDLE, "source")
                        .addAnnotation(NonNull::class.java)
                        .build(),
                ParameterSpec.builder(String::class.java, "key")
                        .addAnnotation(NonNull::class.java)
                        .build()
        )
        this.typeSpec = TypeSpec.classBuilder(TYPE_BUNDLER_UTILS.simpleName())
                .addModifiers(PUBLIC, FINAL)
        this.methodSpecGet = MethodSpec.methodBuilder("getParceler")
                .addTypeVariable(generic)
                .addModifiers(PUBLIC, STATIC, FINAL)
                .addParameters(parameterSpecs)
                .addParameter(ParameterSpec.builder(generic, "defaultValue")
                        .addAnnotation(Nullable::class.java)
                        .build())
                .addCode(CodeBlock.of("if (source.containsKey(key))\n" + "return \$T.unwrap(source.getParcelable(key));\n", TYPE_PARCELS))
                .addStatement("return defaultValue")
                .returns(generic)
        this.methodSpecPut = MethodSpec.methodBuilder("putParceler")
                .addTypeVariable(generic)
                .addModifiers(PUBLIC, STATIC, FINAL)
                .addParameters(parameterSpecs)
                .addParameter(ParameterSpec.builder(generic, "value")
                        .addAnnotation(NonNull::class.java)
                        .build())
                .addStatement("source.putParcelable(key, \$T.wrap(value))", TYPE_PARCELS)
    }

    override val packageName: String get() = "com.hendraanggrian.bundler"

    override val methodSpecs: Iterable<MethodSpec.Builder> get() = listOf(methodSpecGet, methodSpecPut)
}