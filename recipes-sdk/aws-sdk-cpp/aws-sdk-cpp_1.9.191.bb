# -*- mode: Conf; -*-
SUMMARY = "AWS C++ SDK"
HOMEPAGE = "https://github.com/aws/aws-sdk-cpp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = " \
    git://github.com/aws/aws-sdk-cpp.git;protocol=https;branch=main \
    file://0001-build-don-t-use-Werror.patch \
    file://0002-build-fix-building-without-external-dependencies.patch \
"
SRCREV = "b52ce4809f9f0c352f282f981483d1879bc5ed3f"

S = "${WORKDIR}/git"

inherit cmake

DEPENDS = " \
    curl \
    aws-crt-cpp \
    aws-c-auth \
"

PACKAGES =+ " \
    ${PN}-access-management \
    ${PN}-cloudfront \
    ${PN}-cognito-identity \
    ${PN}-cognito-idp \
    ${PN}-iam \
    ${PN}-iot \
    ${PN}-kinesis \
    ${PN}-lambda \
    ${PN}-s3 \
    ${PN}-sts \
"

PROVIDES += "${PACKAGES}"

FILES:${PN} += "${libdir}/libaws-cpp-sdk-core.so"
FILES:${PN}-access-management = "${libdir}/libaws-cpp-sdk-access-management.so"
FILES:${PN}-cloudfront = "${libdir}/libaws-cpp-sdk-cloudfront.so"
FILES:${PN}-cognito-identity = "${libdir}/libaws-cpp-sdk-cognito-identity.so"
FILES:${PN}-cognito-idp = "${libdir}/libaws-cpp-sdk-cognito-idp.so"
FILES:${PN}-iam = "${libdir}/libaws-cpp-sdk-iam.so"
FILES:${PN}-iot = "${libdir}/libaws-cpp-sdk-iot.so"
FILES:${PN}-kinesis = "${libdir}/libaws-cpp-sdk-kinesis.so"
FILES:${PN}-lambda = "${libdir}/libaws-cpp-sdk-lambda.so"
FILES:${PN}-s3 = "${libdir}/libaws-cpp-sdk-s3.so"
FILES:${PN}-sts = "${libdir}/libaws-cpp-sdk-sts.so"

FILES_SOLIBSDEV = ""

# We can't have spaces in -DBUILD_ONLY, hence the strange formatting
EXTRA_OECMAKE += "-DBUILD_ONLY='\
access-management;\
cloudfront;\
cognito-identity;\
cognito-idp;\
iam;\
iot;\
kinesis;\
lambda;\
s3;\
sts;\
'"
EXTRA_OECMAKE += "-DBUILD_DEPS=OFF"
EXTRA_OECMAKE += "-DENABLE_TESTING=OFF"
EXTRA_OECMAKE += "-DAUTORUN_UNIT_TESTS=OFF"
