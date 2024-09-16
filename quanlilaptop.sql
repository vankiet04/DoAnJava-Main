-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 15, 2024 at 10:36 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlilaptop`
--

-- --------------------------------------------------------

--
-- Table structure for table `chucnangnhomquyen`
--

CREATE TABLE `chucnangnhomquyen` (
  `manhomquyen` int(11) NOT NULL,
  `machucnang` varchar(255) NOT NULL,
  `hanhdong` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chucnangnhomquyen`
--

INSERT INTO `chucnangnhomquyen` (`manhomquyen`, `machucnang`, `hanhdong`) VALUES
(1, 'khachhang', 'create'),
(1, 'khachhang', 'delete'),
(1, 'khachhang', 'update'),
(1, 'khachhang', 'view'),
(1, 'nhacungcap', 'create'),
(1, 'nhacungcap', 'delete'),
(1, 'nhacungcap', 'update'),
(1, 'nhacungcap', 'view'),
(1, 'nhanvien', 'create'),
(1, 'nhanvien', 'delete'),
(1, 'nhanvien', 'update'),
(1, 'nhanvien', 'view'),
(1, 'nhaphang', 'create'),
(1, 'nhaphang', 'delete'),
(1, 'nhaphang', 'update'),
(1, 'nhaphang', 'view'),
(1, 'nhomquyen', 'create'),
(1, 'nhomquyen', 'delete'),
(1, 'nhomquyen', 'update'),
(1, 'nhomquyen', 'view'),
(1, 'sanpham', 'create'),
(1, 'sanpham', 'delete'),
(1, 'sanpham', 'update'),
(1, 'sanpham', 'view'),
(1, 'taikhoan', 'create'),
(1, 'taikhoan', 'delete'),
(1, 'taikhoan', 'update'),
(1, 'taikhoan', 'view'),
(1, 'thongke', 'create'),
(1, 'thongke', 'delete'),
(1, 'thongke', 'update'),
(1, 'thongke', 'view'),
(1, 'thuoctinh', 'create'),
(1, 'thuoctinh', 'delete'),
(1, 'thuoctinh', 'update'),
(1, 'thuoctinh', 'view'),
(1, 'xuathang', 'create'),
(1, 'xuathang', 'delete'),
(1, 'xuathang', 'update'),
(1, 'xuathang', 'view');

-- --------------------------------------------------------

--
-- Table structure for table `ctphieunhap`
--

CREATE TABLE `ctphieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `maphienbansp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` bigint(20) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctphieunhap`
--

INSERT INTO `ctphieunhap` (`maphieunhap`, `maphienbansp`, `soluong`, `dongia`) VALUES
(1, 37, 2, 22590000),
(1, 34, 1, 15000000),
(1, 29, 1, 16500000),
(1, 30, 1, 15990000),
(1, 6, 1, 19000000),
(2, 3, 2, 4000000),
(2, 7, 2, 29000000),
(3, 40, 2, 38990000),
(3, 39, 1, 30190000),
(4, 13, 2, 21000000),
(4, 14, 2, 11000000),
(4, 15, 3, 10000000),
(5, 24, 1, 25000000),
(6, 16, 2, 19000000),
(7, 35, 10, 17500000),
(8, 42, 1, 23990000),
(8, 41, 1, 19500000),
(8, 20, 1, 10000000),
(8, 24, 1, 25000000),
(8, 27, 1, 24500000),
(8, 37, 1, 22590000),
(9, 10, 1, 29000000),
(9, 11, 2, 19000000),
(9, 12, 3, 9000000),
(10, 31, 2, 12599000),
(11, 26, 4, 16500000),
(12, 25, 10, 18500000),
(12, 44, 5, 21500000),
(13, 36, 10, 22590000),
(14, 34, 2, 15000000),
(15, 4, 2, 29000000),
(15, 6, 2, 19000000),
(15, 5, 5, 26000000),
(16, 28, 4, 21500000),
(17, 36, 1, 22590000),
(18, 33, 2, 15200000),
(18, 32, 2, 22900000),
(18, 27, 2, 24500000),
(18, 23, 2, 22000000),
(18, 10, 2, 29000000),
(18, 37, 2, 22590000),
(18, 38, 2, 32500000),
(18, 35, 3, 17500000),
(18, 1, 3, 10000000),
(19, 33, 7, 15200000),
(19, 32, 4, 22900000),
(19, 29, 3, 16500000),
(19, 22, 10, 21000000),
(19, 21, 10, 18500000),
(20, 38, 2, 32500000);

-- --------------------------------------------------------

--
-- Table structure for table `ctphieuxuat`
--

CREATE TABLE `ctphieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `maphienbansp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctphieuxuat`
--

INSERT INTO `ctphieuxuat` (`maphieuxuat`, `maphienbansp`, `soluong`, `dongia`) VALUES
(1, 4, 1, 22000000),
(3, 5, 2, 38000000),
(3, 13, 1, 39000000),
(4, 34, 1, 17900000),
(5, 3, 1, 6000000),
(5, 4, 1, 22000000),
(5, 10, 1, 39000000),
(6, 28, 1, 23700000),
(7, 25, 2, 44900000),
(8, 24, 1, 32000000),
(9, 5, 1, 19000000),
(11, 13, 1, 39000000),
(12, 5, 1, 19000000),
(12, 7, 1, 39000000),
(12, 16, 1, 39000000),
(13, 27, 1, 26500000);

-- --------------------------------------------------------

--
-- Table structure for table `ctsanpham`
--

CREATE TABLE `ctsanpham` (
  `maimei` varchar(255) NOT NULL,
  `maphienbansp` int(11) NOT NULL,
  `maphieunhap` int(11) NOT NULL,
  `maphieuxuat` int(11) DEFAULT NULL,
  `tinhtrang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctsanpham`
--

INSERT INTO `ctsanpham` (`maimei`, `maphienbansp`, `maphieunhap`, `maphieuxuat`, `tinhtrang`) VALUES
('1047949689', 37, 1, NULL, 1),
('1029001982', 37, 1, NULL, 1),
('1051286004', 34, 1, 4, 1),
('1077697627', 29, 1, NULL, 1),
('1027553503', 30, 1, NULL, 1),
('1032951533', 6, 1, NULL, 1),
('2030972466', 3, 2, 5, 1),
('2027190440', 3, 2, NULL, 1),
('2062733685', 7, 2, 12, 1),
('2085463551', 7, 2, NULL, 1),
('3038066920', 40, 3, NULL, 1),
('3027841320', 40, 3, NULL, 1),
('3020047850', 39, 3, NULL, 1),
('4056693607', 13, 4, 11, 1),
('4035813838', 13, 4, 3, 1),
('4098931685', 14, 4, NULL, 1),
('4056418712', 14, 4, NULL, 1),
('4015508640', 15, 4, NULL, 1),
('4086048510', 15, 4, NULL, 1),
('4028894229', 15, 4, NULL, 1),
('5018128260', 24, 5, 8, 1),
('6011155164', 16, 6, 12, 1),
('6049377305', 16, 6, NULL, 1),
('7086599390', 35, 7, NULL, 1),
('7094888506', 35, 7, NULL, 1),
('7000120338', 35, 7, NULL, 1),
('7013226080', 35, 7, NULL, 1),
('7053056011', 35, 7, NULL, 1),
('7054010715', 35, 7, NULL, 1),
('7093093694', 35, 7, NULL, 1),
('7077594417', 35, 7, NULL, 1),
('7093388529', 35, 7, NULL, 1),
('7019215437', 35, 7, NULL, 1),
('8054199012', 42, 8, NULL, 1),
('8080215531', 41, 8, NULL, 1),
('8076205715', 20, 8, NULL, 1),
('8001265164', 24, 8, NULL, 1),
('8006565386', 27, 8, NULL, 1),
('8069577542', 37, 8, NULL, 1),
('9020772252', 10, 9, 5, 1),
('9045831772', 11, 9, NULL, 1),
('9035054553', 11, 9, NULL, 1),
('9029326191', 12, 9, NULL, 1),
('9067040346', 12, 9, NULL, 1),
('9017834529', 12, 9, NULL, 1),
('10005844284', 31, 10, NULL, 1),
('10097200922', 31, 10, NULL, 1),
('11068998455', 26, 11, NULL, 1),
('11055753251', 26, 11, NULL, 1),
('11061150065', 26, 11, NULL, 1),
('11072514496', 26, 11, NULL, 1),
('12071885457', 25, 12, NULL, 1),
('12032877679', 25, 12, 7, 1),
('12027826700', 25, 12, NULL, 1),
('12033409445', 25, 12, NULL, 1),
('12023261009', 25, 12, 7, 1),
('12064373922', 25, 12, NULL, 1),
('12023893905', 25, 12, NULL, 1),
('12097521988', 25, 12, NULL, 1),
('12076569857', 25, 12, NULL, 1),
('12066127126', 25, 12, NULL, 1),
('12069636950', 44, 12, NULL, 1),
('12033008649', 44, 12, NULL, 1),
('12067175648', 44, 12, NULL, 1),
('12050117615', 44, 12, NULL, 1),
('12000726172', 44, 12, NULL, 1),
('13099009597', 36, 13, NULL, 1),
('13061601538', 36, 13, NULL, 1),
('13076131139', 36, 13, NULL, 1),
('13008420020', 36, 13, NULL, 1),
('13017730272', 36, 13, NULL, 1),
('13018518896', 36, 13, NULL, 1),
('13061547409', 36, 13, NULL, 1),
('13032666439', 36, 13, NULL, 1),
('13053354794', 36, 13, NULL, 1),
('13030740102', 36, 13, NULL, 1),
('14094396032', 34, 14, NULL, 1),
('14046056726', 34, 14, NULL, 1),
('15084232913', 4, 15, 1, 1),
('15047474111', 4, 15, 5, 1),
('15079850419', 6, 15, NULL, 1),
('15011658109', 6, 15, NULL, 1),
('15093600239', 5, 15, 3, 1),
('15096044623', 5, 15, 3, 1),
('15013814297', 5, 15, NULL, 1),
('15034742478', 5, 15, 9, 1),
('15079253952', 5, 15, 12, 1),
('16048868622', 28, 16, NULL, 1),
('16046907355', 28, 16, NULL, 1),
('16078942187', 28, 16, NULL, 1),
('16032574547', 28, 16, 6, 1),
('17048642868', 36, 17, NULL, 1),
('18057815418', 33, 18, NULL, 1),
('18032012694', 33, 18, NULL, 1),
('18099251650', 32, 18, NULL, 1),
('18058062250', 32, 18, NULL, 1),
('18033540715', 27, 18, NULL, 1),
('18071330109', 27, 18, 13, 1),
('18038070536', 23, 18, NULL, 1),
('18065206281', 23, 18, NULL, 1),
('18090853371', 10, 18, NULL, 1),
('18065286245', 10, 18, NULL, 1),
('18030245796', 37, 18, NULL, 1),
('18088214305', 37, 18, NULL, 1),
('18065280224', 38, 18, NULL, 1),
('18066770009', 38, 18, NULL, 1),
('18024965400', 35, 18, NULL, 1),
('18019066168', 35, 18, NULL, 1),
('18003245304', 35, 18, NULL, 1),
('18030217519', 1, 18, NULL, 1),
('18016326419', 1, 18, NULL, 1),
('18007292945', 1, 18, NULL, 1),
('19073712894', 33, 19, NULL, 1),
('19074281445', 33, 19, NULL, 1),
('19078933579', 33, 19, NULL, 1),
('19011213125', 33, 19, NULL, 1),
('19047792105', 33, 19, NULL, 1),
('19081087610', 33, 19, NULL, 1),
('19068367308', 33, 19, NULL, 1),
('19001958122', 32, 19, NULL, 1),
('19046652361', 32, 19, NULL, 1),
('19026478285', 32, 19, NULL, 1),
('19008771017', 32, 19, NULL, 1),
('19091789641', 29, 19, NULL, 1),
('19058815577', 29, 19, NULL, 1),
('19052997859', 29, 19, NULL, 1),
('19027723044', 22, 19, NULL, 1),
('19028695649', 22, 19, NULL, 1),
('19025706780', 22, 19, NULL, 1),
('19057003699', 22, 19, NULL, 1),
('19020627986', 22, 19, NULL, 1),
('19041375788', 22, 19, NULL, 1),
('19062176960', 22, 19, NULL, 1),
('19029303271', 22, 19, NULL, 1),
('19056523344', 22, 19, NULL, 1),
('19045365741', 22, 19, NULL, 1),
('19073203356', 21, 19, NULL, 1),
('19055107118', 21, 19, NULL, 1),
('19089648755', 21, 19, NULL, 1),
('19081902619', 21, 19, NULL, 1),
('19009130841', 21, 19, NULL, 1),
('19027793728', 21, 19, NULL, 1),
('19029052252', 21, 19, NULL, 1),
('19001242602', 21, 19, NULL, 1),
('19023633836', 21, 19, NULL, 1),
('19007737968', 21, 19, NULL, 1),
('20049304076', 38, 20, NULL, 1),
('20013594288', 38, 20, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `danhsachchucnang`
--

CREATE TABLE `danhsachchucnang` (
  `machucnang` varchar(255) NOT NULL,
  `tenchucnang` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `danhsachchucnang`
--

INSERT INTO `danhsachchucnang` (`machucnang`, `tenchucnang`, `trangthai`) VALUES
('khachhang', 'Quản lý khách hàng', 1),
('khuvuckho', 'Quản lý khu vực kho', 1),
('nhacungcap', 'Quản lý nhà cung cấp', 1),
('nhanvien', 'Quản lý nhân viên', 1),
('nhaphang', 'Quản lý nhập hàng', 1),
('nhomquyen', 'Quản lý nhóm quyền', 1),
('sanpham', 'Quản lý sản phẩm', 1),
('taikhoan', 'Quản lý tài khoản', 1),
('thongke', 'Quản lý thống kê', 1),
('thuoctinh', 'Quản lý thuộc tính', 1),
('xuathang', 'Quản lý xuất hàng', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hedieuhanh`
--

CREATE TABLE `hedieuhanh` (
  `MaHDH` int(11) NOT NULL,
  `TenHDH` varchar(255) DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hedieuhanh`
--

INSERT INTO `hedieuhanh` (`MaHDH`, `TenHDH`, `TrangThai`) VALUES
(1, 'Windows', 1),
(2, 'MAC OS', 1),
(3, 'Linux', 1);

-- --------------------------------------------------------

--
-- Table structure for table `huyhoadon`
--

CREATE TABLE `huyhoadon` (
  `mahoadon` int(11) NOT NULL,
  `manv` int(11) NOT NULL,
  `lydohuy` varchar(255) DEFAULT NULL,
  `thoigian` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `huyhoadon`
--

INSERT INTO `huyhoadon` (`mahoadon`, `manv`, `lydohuy`, `thoigian`) VALUES
(10, 1, 'chua thanh toan', '2024-09-15 00:00:00'),
(10, 1, 'a', '2024-09-15 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(11) NOT NULL,
  `TenKH` varchar(255) DEFAULT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `SDT` varchar(255) DEFAULT NULL,
  `NgayThamGia` date DEFAULT NULL,
  `GioiTinh` varchar(255) DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `TenKH`, `DiaChi`, `SDT`, `NgayThamGia`, `GioiTinh`, `TrangThai`) VALUES
(1, 'Nguyễn Văn A', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12', 'Nam', 1),
(2, 'Nguyễn Văn B', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12', 'Nam', 1),
(3, 'Nguyễn Văn C', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12', 'Nam', 1),
(4, 'Nguyễn Văn D', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12', 'Nam', 1),
(5, 'Nguyễn Văn E', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12', 'Nam', 1),
(6, 'Nguyễn Văn F', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12', 'Nam', 1),
(7, 'Lê Văn B', 'TPHCM', '0359197452', '2024-09-15', 'Nam', 1);

-- --------------------------------------------------------

--
-- Table structure for table `kho`
--

CREATE TABLE `kho` (
  `MaKho` int(11) NOT NULL,
  `TenKho` varchar(255) NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kho`
--

INSERT INTO `kho` (`MaKho`, `TenKho`, `TrangThai`) VALUES
(1, 'Kho 1', 1),
(2, 'Kho 2', 1),
(3, 'Kho 3', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `manhacungcap` int(11) NOT NULL,
  `tennhacungcap` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`manhacungcap`, `tennhacungcap`, `diachi`, `email`, `sdt`, `trangthai`) VALUES
(1, 'Công Ty TNHH Thế Giới Di Động', ' Phòng 6.5, Tầng6, Tòa Nhà E. Town 2, 364 Cộng Hòa, P. 13, Q. Tân Bình, Tp. Hồ Chí Minh', 'lienhe@thegioididong.com', '02835100100', 1),
(2, 'Công ty Laptop FPT Việt Nam', 'Số 79 đường số 6, Hưng Phước 4, Phú Mỹ Hưng, quận 7, TPHCM', 'contact@paviet.vn', '0909453111', 1),
(3, 'Công Ty PC TayNguyeSound', '8/38 Đinh Bô Lĩnh, P.24, Q. Bình Thạnh, Tp. Hồ Chí Minh', 'contact@baola.vn', '02835119060', 1),
(4, 'Công Ty ASUS', 'Phòng 703, Tầng7, Tòa Nhà Metropolitan, 235 Đồng Khởi, P. Bến Nghé, Q. 1, Tp. Hồ Chí Minh (TPHCM)', 'chau.nguyen@nokia.com', '02838236894', 1),
(5, 'Hệ Thống Phân Phối Chính Hãng Lenovo Gaming', '261 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', 'info@mihome.vn', '0365888866', 1),
(6, 'Công Ty Macbook Việt Nam', 'Tòa nhà tài chính Bitexco, 2 Hải Triều, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh', 'contact@samsung.vn', '0988788456', 1),
(7, 'Công ty Grear Việt Nam', '27 Đ. Nguyễn Trung Trực, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', 'oppovietnam@oppo.vn', '0456345234', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` int(11) NOT NULL,
  `hoten` varchar(255) NOT NULL,
  `gioitinh` int(11) NOT NULL,
  `ngaysinh` date NOT NULL,
  `sdt` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `hoten`, `gioitinh`, `ngaysinh`, `sdt`, `email`, `trangthai`) VALUES
(1, 'Phạm Văn Kiệt', 1, '2004-12-20', '0976204879', 'phamvankietgmail.com', 1),
(2, 'Nguyễn Phan Tuấn Kiệt', 1, '2004-04-11', '0355374322', 'nguyenphantuankiet@gmail.com', 1),
(3, 'Nguyễn Thế Kiên', 0, '2004-04-11', '0123456789', 'nguyenthekien@gmail.com', 1),
(4, 'Nguyễn Trung Hưng', 0, '2004-04-03', '0123456789', 'hunggaylo@gmail.com', 1),
(5, 'Huỳnh Minh Phúc', 1, '2004-05-06', '0123456789', 'minhphuc@gmail.com', 1),
(6, 'Diệp Thụy An', 1, '2004-05-06', '0123456789', 'thuyan@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhomquyen`
--

CREATE TABLE `nhomquyen` (
  `manhomquyen` int(11) NOT NULL,
  `tennhomquyen` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhomquyen`
--

INSERT INTO `nhomquyen` (`manhomquyen`, `tennhomquyen`, `trangthai`) VALUES
(1, 'Admin', 1);

-- --------------------------------------------------------

--
-- Table structure for table `phienbansanpham`
--

CREATE TABLE `phienbansanpham` (
  `maphienbansp` int(11) NOT NULL,
  `masanpham` int(11) DEFAULT NULL,
  `rom` int(11) DEFAULT NULL,
  `ram` int(11) DEFAULT 0,
  `gianhap` int(11) DEFAULT NULL,
  `giaxuat` int(11) DEFAULT NULL,
  `soluongton` int(11) DEFAULT 0,
  `trangthai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phienbansanpham`
--
INSERT INTO `phienbansanpham` (`maphienbansp`, `masanpham`, `rom`, `ram`, `gianhap`, `giaxuat`, `soluongton`, `trangthai`) VALUES
(1, 1, 512, 16, 0, 0, 3, 1),
(2, 1, 256, 16, 0, 0, 0, 1),
(3, 1, 256, 8, 0, 0, 1, 1),
(4, 2, 512, 16, 0, 0, 0, 1),
(5, 2, 256, 16, 0, 0, 1, 1),
(6, 2, 256, 8, 0, 0, 3, 1),
(7, 3, 512, 16, 0, 0, 1, 1),
(8, 3, 256, 16, 0, 0, 0, 1),
(9, 3, 256, 8, 0, 0, 0, 1),
(10, 4, 512, 16, 0, 0, 2, 1),
(11, 4, 256, 16, 0, 0, 2, 1),
(12, 4, 256, 8, 0, 0, 3, 1),
(13, 5, 512, 16, 0, 0, 0, 1),
(14, 5, 256, 16, 0, 0, 2, 1),
(15, 5, 256, 8, 0, 0, 3, 1),
(16, 6, 512, 16, 0, 0, 1, 1),
(17, 6, 256, 16, 0, 0, 0, 1),
(18, 6, 256, 8, 0, 0, 0, 1),
(19, 7, 512, 16, 0, 0, 0, 1),
(20, 8, 512, 16, 0, 0, 1, 1),
(21, 9, 256, 8, 0, 0, 10, 1),
(22, 10, 512, 16, 0, 0, 10, 1),
(23, 11, 512, 16, 0, 0, 2, 1),
(24, 12, 512, 32, 0, 0, 1, 1),
(25, 13, 512, 16, 0, 0, 8, 1),
(26, 14, 512, 16, 0, 0, 4, 1),
(27, 15, 512, 16, 0, 0, 2, 1),
(28, 16, 256, 8, 0, 0, 3, 1),
(29, 17, 256, 16, 0, 0, 4, 1),
(30, 18, 512, 16, 0, 0, 1, 1),
(31, 19, 512, 16, 0, 0, 2, 1),
(32, 20, 256, 8, 0, 0, 6, 1),
(33, 21, 256, 16, 0, 0, 9, 1),
(34, 22, 512, 16, 0, 0, 2, 1),
(35, 23, 512, 16, 0, 0, 13, 1),
(36, 24, 1024, 16, 0, 0, 11, 1),
(37, 25, 512, 16, 0, 0, 5, 1),
(38, 26, 256, 16, 0, 0, 4, 1),
(39, 26, 256, 8, 0, 0, 1, 1),
(40, 26, 512, 16, 0, 0, 2, 1),
(41, 7, 1024, 16, 0, 0, 1, 1),
(42, 7, 512, 32, 0, 0, 1, 1),
(43, 8, 512, 8, 0, 0, 0, 1),
(44, 13, 1024, 16, 0, 0, 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `thoigian` datetime DEFAULT current_timestamp(),
  `manhacungcap` int(11) NOT NULL,
  `nguoitao` int(11) NOT NULL,
  `tongtien` bigint(20) NOT NULL DEFAULT 0,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`maphieunhap`, `thoigian`, `manhacungcap`, `nguoitao`, `tongtien`, `trangthai`) VALUES
(1, '2024-09-15 15:15:39', 2, 1, 111670000, 1),
(2, '2024-09-15 15:15:57', 1, 1, 66000000, 1),
(3, '2024-09-15 15:16:24', 7, 1, 108170000, 1),
(4, '2024-09-15 15:16:49', 4, 1, 94000000, 1),
(5, '2024-09-15 15:17:01', 5, 1, 25000000, 1),
(6, '2024-09-15 15:17:10', 3, 1, 38000000, 1),
(7, '2024-09-15 15:17:27', 7, 1, 175000000, 1),
(8, '2024-09-15 15:18:07', 4, 1, 125580000, 1),
(9, '2024-09-15 15:18:49', 6, 1, 94000000, 1),
(10, '2024-09-15 15:19:03', 3, 1, 25198000, 1),
(11, '2024-09-15 15:19:21', 7, 1, 66000000, 1),
(12, '2024-09-15 15:19:49', 2, 1, 292500000, 1),
(13, '2024-09-15 15:20:27', 4, 1, 225900000, 1),
(14, '2024-09-15 15:20:43', 2, 1, 30000000, 1),
(15, '2024-09-15 15:21:17', 3, 1, 226000000, 1),
(16, '2024-09-15 15:21:34', 5, 1, 86000000, 1),
(17, '2024-09-15 15:21:45', 1, 1, 22590000, 1),
(18, '2024-09-15 15:28:52', 2, 1, 419880000, 1),
(19, '2024-09-15 15:29:34', 4, 1, 642500000, 1),
(20, '2024-09-15 15:29:49', 1, 1, 65000000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `thoigian` datetime DEFAULT current_timestamp(),
  `manv` int(11) NOT NULL,
  `makh` int(11) NOT NULL,
  `tongtien` bigint(20) NOT NULL DEFAULT 0,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieuxuat`
--

INSERT INTO `phieuxuat` (`maphieuxuat`, `thoigian`, `manv`, `makh`, `tongtien`, `trangthai`) VALUES
(1, '2024-09-15 15:23:09', 1, 6, 22000000, 1),
(2, '2024-09-15 15:23:24', 1, 4, 0, 1),
(3, '2024-09-15 15:23:51', 1, 1, 77000000, 1),
(4, '2024-09-15 15:24:17', 1, 1, 17900000, 1),
(5, '2024-09-15 15:25:28', 1, 2, 67000000, 1),
(6, '2024-09-15 15:25:47', 1, 5, 23700000, 1),
(7, '2024-09-15 15:26:44', 1, 7, 44900000, 1),
(8, '2024-09-15 15:27:00', 1, 6, 32000000, 1),
(9, '2024-09-15 15:27:38', 1, 2, 19000000, 1),
(11, '2024-09-15 15:30:48', 1, 7, 39000000, 1),
(12, '2024-09-15 15:31:24', 1, 7, 97000000, 1),
(13, '2024-09-15 15:35:53', 1, 3, 26500000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ram`
--

CREATE TABLE `ram` (
  `MaRAM` int(11) NOT NULL,
  `KichThuocRAM` int(11) DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ram`
--

INSERT INTO `ram` (`MaRAM`, `KichThuocRAM`, `TrangThai`) VALUES
(1, 8, 1),
(2, 16, 1),
(3, 32, 1),
(4, 64, 1);

-- --------------------------------------------------------

--
-- Table structure for table `rom`
--

CREATE TABLE `rom` (
  `MaROM` int(11) NOT NULL,
  `KichThuocROM` int(11) DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rom`
--

INSERT INTO `rom` (`MaROM`, `KichThuocROM`, `TrangThai`) VALUES
(1, 256, 1),
(2, 512, 1),
(3, 1024, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(255) DEFAULT NULL,
  `hinhanh` varchar(255) DEFAULT NULL,
  `boxuly` varchar(255) DEFAULT NULL,
  `hedieuhanh` varchar(255) DEFAULT NULL,
  `thoigianbaohanh` int(11) DEFAULT NULL,
  `maloai` int(11) DEFAULT NULL,
  `khuvuckho` varchar(255) DEFAULT NULL,
  `thuonghieu` varchar(255) DEFAULT NULL,
  `trangthai` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`masanpham`, `tensanpham`, `hinhanh`, `boxuly`, `hedieuhanh`, `thoigianbaohanh`, `maloai`, `khuvuckho`, `thuonghieu`, `trangthai`) VALUES
(1, 'LÁP TÓP GA MINH', 'warning.png', 'I9 202444', 'MAC OS', 2, 0, 'Kho 1', 'Apple', 1),
(2, 'LENOVO LEGION 5', 'warning.png', ' i7 1355U', 'Windows', 1, 9, 'Kho 2', 'Lenovo', 1),
(3, 'MACBOOK PRO 14 Inch', 'warning.png', 'M2', 'MAC OS', 2, 0, 'Kho 1', 'Apple', 1),
(4, 'MACBOOK AIR M2', 'warning.png', 'M2', 'MAC OS', 1, 0, 'Kho 1', 'Apple', 1),
(5, 'ACER NITRO 5', 'warning.png', ' i7 1355U', 'Win 11', 0, 0, 'Kho 2', 'ACER', 1),
(6, 'MSI GAMING XF', 'warning.png', ' i5 11400H', 'Win 11', 2, 0, 'Kho 1', 'MSI', 1),
(7, 'Asus TUF Ryzen 7', 'laptop.jpg', 'AMD 6700', 'Windows', 0, 9, 'Kho 2', 'Asus', 1),
(8, 'Asus VivoBook', 'laptop.jpg', 'AMD 5600', 'Windows', 0, 9, 'Kho 2', 'Asus', 1),
(9, 'Levono ThinkPad', 'laptop.jpg', 'Intel Core i5 13420H', 'Windows', 0, 9, 'Kho 1', 'Levono', 1),
(10, 'MacBook Pro 13 M2', 'laptop.jpg', 'M2', 'MAC OS', 0, 9, 'Kho 2', 'Apple', 1),
(11, 'MacBook Air M1', 'laptop.jpg', 'M1', 'MAC OS', 0, 1, 'Kho 1', 'Apple', 1),
(12, 'Asus ROG G16', 'laptop.jpg', 'AMD Ryzen', 'Windows', 0, 9, 'Kho 1', 'Asus', 1),
(13, 'Dell XPS15', 'laptop.jpg', 'Core i7 11800H', 'Windows', 0, 9, 'Kho 3', 'Dell', 1),
(14, 'Asus VivoBook Pro 15', 'laptop.jpg', 'Core i7 12450H', 'Windows', 0, 9, 'Kho 2', 'Asus', 1),
(15, 'Asus ZenBook', 'laptop.jpg', 'Intel Evo', 'Windows', 0, 1, 'Kho 2', 'Asus', 1),
(16, 'Levono ThinkPad X1', 'laptop.jpg', 'Core i7 10250H', 'Windows', 0, 9, 'Kho 1', 'Levono', 1),
(17, 'Dell Inspiron', 'laptop.jpg', 'Intel core i5 10200H', 'Windows', 0, 9, 'Kho 1', 'Dell', 1),
(18, 'Dell Latitude', 'laptop.jpg', 'Core i5 1145G7', 'Windows', 0, 9, 'Kho 1', 'Dell', 1),
(19, 'Dell Vostro', 'laptop.jpg', 'Core i5 1335U', 'Windows', 0, 9, 'Kho 2', 'Dell', 1),
(20, 'HP Spectre', 'laptop.jpg', 'Core i5 9750U', 'Windows', 0, 9, 'Kho 1', 'PHP', 1),
(21, 'HP Probook', 'laptop.jpg', 'Intel core i7 1335U', 'Windows', 0, 9, 'Kho 2', 'PHP', 1),
(22, 'HPZBook', 'laptop.jpg', 'Intel Core i7 10200H', 'Windows', 0, 9, 'Kho 3', 'PHP', 1),
(23, 'MSI Gaming GF63', 'laptop.jpg', 'Intel Core i5 12450H', 'Windows', 0, 1, 'Kho 1', 'Apple', 1),
(24, 'MSI Gaming Katana 15', 'laptop.jpg', 'Intel core i7 13620H', 'Windows', 0, 1, 'Kho 2', 'MSI', 1),
(25, 'Asus ZenBook 14 OLED', 'laptop.jpg', 'Intel core i5 13500H', 'Windows', 0, 1, 'Kho 2', 'Asus', 1),
(26, 'Macbook Air 15 M3', 'laptop.jpg', 'M3', 'MAC OS', 0, 1, 'Kho 2', 'Apple', 1);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `manv` int(11) NOT NULL,
  `matkhau` varchar(255) NOT NULL,
  `manhomquyen` int(11) NOT NULL,
  `tendangnhap` varchar(50) NOT NULL DEFAULT '',
  `trangthai` int(11) NOT NULL,
  `otp` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`manv`, `matkhau`, `manhomquyen`, `tendangnhap`, `trangthai`, `otp`) VALUES
(1, '123456', 1, 'admin', 1, 'null');

-- --------------------------------------------------------

--
-- Table structure for table `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `tenthuonghieu` varchar(255) DEFAULT NULL,
  `trangthai` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thuonghieu`
--

INSERT INTO `thuonghieu` (`tenthuonghieu`, `trangthai`) VALUES
('Apple', 1),
('Asus', 1),
('PHP', 1),
('Levono', 1),
('Dell', 1),
('ACER', 1),
('MSI', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  ADD PRIMARY KEY (`manhomquyen`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  MODIFY `manhomquyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
