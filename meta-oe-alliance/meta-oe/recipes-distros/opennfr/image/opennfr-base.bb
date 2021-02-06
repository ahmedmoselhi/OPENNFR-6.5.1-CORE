SUMMARY = "Base packages require for image."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
			file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

ALLOW_EMPTY_${PN} = "1"

PV = "${IMAGE_VERSION}"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = "\
	ca-certificates \
	hddtemp \
	oe-alliance-base \
	opennfr-enigma2 \
	opennfr-bootlogo \
	opennfr-version-info \
	openssh-sftp-server \
	${@bb.utils.contains("PYTHON_PN", "python", "${PYTHON_PN}-imaging", "${PYTHON_PN}-pillow", d)} \
	${PYTHON_PN}-cfscrape \
	${PYTHON_PN}-js2py \
	${PYTHON_PN}-requests \
	${PYTHON_PN}-ipaddress  \
	${PYTHON_PN}-netifaces \
	${PYTHON_PN}-pyexecjs \
	${PYTHON_PN}-asn1crypto \
	${PYTHON_PN}-incremental \
	${PYTHON_PN}-constantly \
	${PYTHON_PN}-hyperlink \
	${PYTHON_PN}-service-identity \
	${PYTHON_PN}-future \
	${PYTHON_PN}-six \
	${PYTHON_PN}-gdata \
	${PYTHON_PN}-beautifulsoup4 \
	udpxy \
	uchardet \
	nodejs \
	ofgwrite \
	libshowiframe \
	dvbsnoop \
	librtmp \
	rtmpdump \
	iperf3 \
	zip \
	packagegroup-base-smbfs-client \
	packagegroup-base-smbfs-utils \
	packagegroup-base-smbfs-server \
	packagegroup-base-nfs \
	enigma2-plugin-drivers-usbserial \
	${@bb.utils.contains("TUNE_FEATURES", "armv", "glibc-compat", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "smallflash", "", " \
	iproute2 \
	tar \
	", d)} \
	${@bb.utils.contains_any("FLASHSIZE", "64 96", "", " \
	ntfs-3g \
	unrar \
	", d)} \
	"
