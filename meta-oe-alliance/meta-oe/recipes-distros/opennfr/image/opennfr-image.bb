DESCRIPTION = "OPENNFR Image"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "OPENNFR team"

require conf/license/license-gplv2.inc

PV = "${IMAGE_VERSION}"
PR = "${BUILD_VERSION}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

WORKDIR = "${TMPDIR}/work/${MULTIMACH_TARGET_SYS}/${PN}/${EXTENDPE}${PV}"

do_rootfs[deptask] = "do_rm_work"

IMAGE_INSTALL = "opennfr-base \
	${@bb.utils.contains("MACHINE_FEATURES", "dvbc-only", "", "enigma2-plugin-settings-defaultsat", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "no-cl-svr", "", \
	" \
	packagegroup-base-smbfs-client \
	packagegroup-base-smbfs-server \
	packagegroup-base-nfs \
	", d)} \
	"

IMAGE_FEATURES += "package-management"

inherit image

image_preprocess() {
			cd ${IMAGE_ROOTFS}/media
			mkdir -p ${IMAGE_ROOTFS}/media/card
			mkdir -p ${IMAGE_ROOTFS}/media/cf
			mkdir -p ${IMAGE_ROOTFS}/media/hdd
			mkdir -p ${IMAGE_ROOTFS}/media/hdd1
			mkdir -p ${IMAGE_ROOTFS}/media/hdd2
			mkdir -p ${IMAGE_ROOTFS}/media/hdd3
			mkdir -p ${IMAGE_ROOTFS}/media/net
			mkdir -p ${IMAGE_ROOTFS}/media/upnp
			mkdir -p ${IMAGE_ROOTFS}/media/usb
			mkdir -p ${IMAGE_ROOTFS}/media/usb1
			mkdir -p ${IMAGE_ROOTFS}/media/usb2
			mkdir -p ${IMAGE_ROOTFS}/media/usb3
			touch ${IMAGE_ROOTFS}/media/hdd/.fstab
			touch ${IMAGE_ROOTFS}/media/hdd1/.fstab
			touch ${IMAGE_ROOTFS}/media/hdd2/.fstab
			touch ${IMAGE_ROOTFS}/media/hdd3/.fstab
			touch ${IMAGE_ROOTFS}/media/usb/.fstab
			touch ${IMAGE_ROOTFS}/media/usb1/.fstab
			touch ${IMAGE_ROOTFS}/media/usb2/.fstab
			touch ${IMAGE_ROOTFS}/media/usb3/.fstab
			cd $curdir

}

INHIBIT_DEFAULT_DEPS = "1"

do_package_index[nostamp] = "1"
do_package_index[depends] += "${PACKAGEINDEXDEPS}"

python do_package_index() {
    from oe.rootfs import generate_index_files
    generate_index_files(d)
}

IMAGE_PREPROCESS_COMMAND += "image_preprocess; "

addtask do_package_index after do_rootfs before do_image_complete
