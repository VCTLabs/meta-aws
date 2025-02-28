# -*- mode: Conf; -*-
SUMMARY = "Amazon Kinesis Video Streams C Producer"
DESCRIPTION = "Amazon Kinesis Video Streams Producer SDK for C/C++ makes it easy to build an on-device application that securely connects to a video stream, and reliably publishes video and other media data to Kinesis Video Streams. It takes care of all the underlying tasks required to package the frames and fragments generated by the device's media pipeline. The SDK also handles stream creation, token rotation for secure and uninterrupted streaming, processing acknowledgements returned by Kinesis Video Streams, and other tasks."
HOMEPAGE = "https://github.com/awslabs/amazon-kinesis-video-streams-producer-c"
LICENSE = "Apache-2.0"
PROVIDES += "aws/amazon-kvs-producer-sdk-c"

inherit cmake
inherit pkgconfig

BRANCH ?= "master"
SDIR ?= "amazon-kvs-producer-sdk-c"

LIC_FILES_CHKSUM = "file://${SDIR}/LICENSE;md5=34400b68072d710fecd0a2940a0d1658"

SRC_URI = "git://github.com/awslabs/amazon-kinesis-video-streams-producer-c.git;protocol=https;branch=${BRANCH};destsuffix=${S}/${SDIR} \
           file://amazon-kvs-producer-sdk-c-deps.patch"

SRCREV = "99c1a8cd8cec88f99c9c4ce3944b53ae341d1491"
S = "${WORKDIR}/git"

DEPENDS = "openssl curl gtest jsmn amazon-kvs-producer-pic mbedtls libwebsockets"
RDEPENDS:${PN} = ""
CFLAGS:append = " -Wl,-Bsymbolic"
OECMAKE_BUILDPATH += "${WORKDIR}/build"
OECMAKE_SOURCEPATH += "${S}/${SDIR}"

EXTRA_OECMAKE += "-DBUILD_DEPENDENCIES=OFF"
EXTRA_OECMAKE += "-DBUILD_TEST=OFF"
EXTRA_OECMAKE += "-DCODE_COVERAGE=OFF"
EXTRA_OECMAKE += "-DCOMPILER_WARNINGS=OFF"
EXTRA_OECMAKE += "-DADDRESS_SANITIZER=OFF"
EXTRA_OECMAKE += "-DMEMORY_SANITIZER=OFF"
EXTRA_OECMAKE += "-DTHREAD_SANITIZER=OFF"
EXTRA_OECMAKE += "-DUNDEFINED_BEHAVIOR_SANITIZER=OFF"
EXTRA_OECMAKE += "-DDEBUG_HEAP=OFF"
EXTRA_OECMAKE += "-DALIGNED_MEMORY_MODEL=OFF"

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"
EXTRA_OECMAKE += "-DCMAKE_INSTALL_PREFIX=$D/usr"

FILES:${PN}     = "${libdir}/libcproducer.so \
                   ${libdir}/pkgconfig/*.pc"
FILES:${PN}-dev = "${includedir}/com/amazonaws/kinesis/video/*"
FILES:${PN}-dbg = ""

# Notify that libraries are not versioned
FILES_SOLIBSDEV = ""

BBCLASSEXTEND = "native nativesdk"
