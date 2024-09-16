SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
CREATE DATABASE IF NOT EXISTS quanlilaptop;
USE quanlilaptop;

CREATE TABLE IF NOT EXISTS `taikhoan` (
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


INSERT INTO `sanpham` (`masanpham`, `tensanpham`, `hinhanh`, `boxuly`, `hedieuhanh`, `thoigianbaohanh`, `maloai`, `khuvuckho`, `thuonghieu`,  `trangthai`) 
VALUES (1, 'LÁP TÓP GA MINH', 'warning.png', 'I9 202444', 'MAC OS', '1 năm', 'GM1', 'Kho A', 'Apple', 1),
(2, 'LENOVO LEGION 5', 'warning.png', ' i7 1355U', 'Win 11', '1 năm', 'LG5', 'Kho B', 'Lenovo', 1),
(3, 'MACBOOK PRO 14 Inch', 'warning.png', 'M2', 'MAC OS', '1 năm', 'MACP', 'Kho B', 'Apple', 1),
(4, 'MACBOOK AIR M2', 'warning.png', 'M2', 'MAC OS', '1 năm', 'MACA', 'Kho B', 'Apple', 1),
(5, 'ACER NITRO 5', 'warning.png', ' i7 1355U', 'Win 11', '1 năm', 'DI15', 'Kho A', 'ACER', 1),
(6, 'MSI GAMING XF', 'warning.png', ' i5 11400H', 'Win 11', '1 năm', 'DI14', 'Kho A', 'MSI', 1);

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

INSERT INTO `phienbansanpham` (`maphienbansp`, `masanpham`, `rom`, `ram`, `gianhap`, `giaxuat`, `soluongton`, `trangthai`) VALUES
(1, 1, 512, 16, 10000000, 15000000, 0, 1),
(2, 1, 256, 16, 5000000, 9000000, 0, 1),
(3, 1, 256, 8, 4000000, 6000000, 0, 1),
(4, 2, 512, 16, 29000000, 22000000, 0, 1),
(5, 2, 256, 16, 26000000, 19000000, 0, 1),
(6, 2, 256, 8, 19000000, 16000000, 0, 1),
(7, 3, 512, 16, 29000000, 39000000, 0, 1),
(8, 3, 256, 16, 26000000, 29000000, 0, 1),
(9, 3, 256, 8, 19000000, 24000000, 0, 1),
(10, 4, 512, 16, 29000000, 39000000, 0, 1),
(11, 4, 256, 16, 19000000, 29000000, 0, 1),
(12, 4, 256, 8, 9000000, 19000000, 0, 1),
(13, 5, 512, 16, 21000000, 39000000, 0, 1),
(14, 5, 256, 16, 11000000, 29000000, 0, 1),
(15, 5, 256, 8, 10000000, 24000000, 0, 1),
(16, 6, 512, 16, 19000000, 39000000, 0, 1),
(17, 6, 256, 16, 14000000, 29000000, 0, 1),
(18, 6, 256, 8, 9000000, 24000000, 0, 1);




CREATE TABLE `thuonghieu` (
  `tenthuonghieu` varchar(255) DEFAULT NULL,
  `trangthai` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `thuonghieu` (`tenthuonghieu`, `trangthai`) VALUES
('Apple', 1);


CREATE TABLE `kho` (
  `MaKho` int(11) NOT NULL,
  `TenKho` varchar(255) NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `kho` (`MaKho`, `TenKho`, `TrangThai`) VALUES
(1, 'Kho 1', 1),
(2, 'Kho 2', 1),
(3, 'Kho 3', 1);

CREATE TABLE `RAM` (
  `MaRAM` int(11) NOT NULL,
  `KichThuocRAM` int(11) DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `RAM` (`MaRAM`, `KichThuocRAM`, `TrangThai`) VALUES
(1, 8, 1),
(2, 16, 1),
(3, 32, 1);

CREATE TABLE `ROM` (
  `MaROM` int(11) NOT NULL,
  `KichThuocROM` int(11) DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `ROM` (`MaROM`, `KichThuocROM`, `TrangThai`) VALUES
(1, 256, 1),
(2, 512, 1),
(3, 1024, 1);

CREATE TABLE `hedieuhanh` (
  `MaHDH` int(11) NOT NULL,
  `TenHDH` varchar(255) DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `hedieuhanh` (`MaHDH`, `TenHDH`, `TrangThai`) VALUES
(1, 'Windows', 1),
(2, 'MAC OS', 1),
(3, 'Linux', 1);

CREATE TABLE `nhacungcap` (
  `manhacungcap` int(11) NOT NULL,
  `tennhacungcap` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `huyhoadon` (
  `mahoadon` int(11) NOT NULL,
  `manv` int(11) NOT NULL,
  `lydohuy` varchar(255) DEFAULT NULL,
  `thoigian` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`manhacungcap`, `tennhacungcap`, `diachi`, `email`, `sdt`, `trangthai`)  VALUES
(1, 'Công Ty TNHH Thế Giới Di Động', ' Phòng 6.5, Tầng6, Tòa Nhà E. Town 2, 364 Cộng Hòa, P. 13, Q. Tân Bình, Tp. Hồ Chí Minh', 'lienhe@thegioididong.com', '02835100100', 1),
(2, 'Công ty Laptop FPT Việt Nam', 'Số 79 đường số 6, Hưng Phước 4, Phú Mỹ Hưng, quận 7, TPHCM', 'contact@paviet.vn', '19009477', 1),
(3, 'Công Ty PC TayNguyeSound', '8/38 Đinh Bô Lĩnh, P.24, Q. Bình Thạnh, Tp. Hồ Chí Minh', 'contact@baola.vn', '02835119060', 1),
(4, 'Công Ty ASUS', 'Phòng 703, Tầng7, Tòa Nhà Metropolitan, 235 Đồng Khởi, P. Bến Nghé, Q. 1, Tp. Hồ Chí Minh (TPHCM)', 'chau.nguyen@nokia.com', '02838236894', 1),
(5, 'Hệ Thống Phân Phối Chính Hãng Lenovo Gaming', '261 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', 'info@mihome.vn', '0365888866', 1),
(6, 'Công Ty Macbook Việt Nam', 'Tòa nhà tài chính Bitexco, 2 Hải Triều, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh', 'contact@samsung.vn', '0988788456', 1),
(7, 'Công ty Grear Việt Nam', '27 Đ. Nguyễn Trung Trực, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', 'oppovietnam@oppo.vn', '0456345234', 1);

-- --------------------------------------------------------

CREATE TABLE `KhachHang` (
  `MaKH` int(11) NOT NULL,
  `TenKH` varchar(255) DEFAULT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `SDT` varchar(255) DEFAULT NULL,
  `NgayThamGia` date DEFAULT NULL,
  `GioiTinh` varchar(255) DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `KhachHang` (`MaKH`, `TenKH`, `DiaChi`, `SDT`, `NgayThamGia`, `GioiTinh`, `TrangThai`) VALUES
(1, 'Nguyễn Văn A', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12', 'Nam', 1),
(2, 'Nguyễn Văn B', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12', 'Nam', 1),
(3, 'Nguyễn Văn C', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12', 'Nam', 1),
(4, 'Nguyễn Văn D', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12', 'Nam', 1),
(5, 'Nguyễn Văn E', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12','Nam', 1),
(6, 'Nguyễn Văn F', '123 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '0365888866', '2022-12-12', 'Nam', 1);


CREATE TABLE `phieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `thoigian` datetime DEFAULT current_timestamp(),
  `manhacungcap` int(11) NOT NULL,
  `nguoitao` int(11) NOT NULL,
  `tongtien` bigint(20) NOT NULL DEFAULT 0,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




CREATE TABLE `ctphieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `maphienbansp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctphieunhap`
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

-- INSERT INTO `ctsanpham` (`maimei`, `maphienbansp`, `maphieunhap`, `maphieuxuat`, `tinhtrang`) VALUES
-- (107725056444797, 1, 1, NULL, 1),
-- (107725056444798, 2, 1, NULL, 1), 
-- (107725056444799, 3, 1, NULL, 1),
-- (107725056444800, 4, 2, NULL, 1),
-- (107725056444801, 5, 2, NULL, 1),
-- (107725056444802, 6, 2, NULL, 1),
-- (107725056444803, 7, 3, NULL, 1),
-- (111111111111111, 8, 3, NULL, 1),
-- (111111111111112, 9, 3, NULL, 1);

CREATE TABLE `nhomquyen` (
  `manhomquyen` int(11) NOT NULL AUTO_INCREMENT,
  `tennhomquyen` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL,
  PRIMARY KEY (`manhomquyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `danhsachchucnang` (
  `machucnang` varchar (255) NOT NULL,
  `tenchucnang` nvarchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `chucnangnhomquyen` (
  `manhomquyen` int(11) NOT NULL,
  `machucnang` varchar(255) NOT NULL,
  `hanhdong` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `nhomquyen` (`manhomquyen`, `tennhomquyen`, `trangthai`) VALUES
(1, 'Admin', 1);


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

CREATE TABLE `phieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `thoigian` datetime DEFAULT current_timestamp(),
  `manv` int(11) NOT NULL,
  `makh` int(11) NOT NULL,
  `tongtien` bigint(20) NOT NULL DEFAULT 0,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `ctphieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `maphienbansp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

COMMIT;