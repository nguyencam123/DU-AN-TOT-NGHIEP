import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addProductAsync } from "../../features/product/createproductThunks";
import { fetchProducts, getProducts } from "../../features/product/productThunk";
import { fetchCategory } from "../../features/category/categoryThunk"
import { useState } from "react";
import { Space, Table, Typography, Modal, Spin, Popconfirm, Form, Input, Row, Col, Select, Button, Pagination, Image } from 'antd';
import { DeleteOutlined, EditOutlined, EyeOutlined, QuestionCircleOutlined, RotateLeftOutlined, RotateRightOutlined, SwapOutlined, ZoomInOutlined, ZoomOutOutlined } from '@ant-design/icons';
import { removeProduct } from "../../features/product/deleteproductThunks";
import * as Yup from 'yup'
import { DetailHomestay } from "../user/hotelcomponent/detailHomestay";
import moment from 'moment';


const { Title } = Typography;

const onFinish = (values) => {
  console.log('Success:', values);
};
const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};
const handleChange = (value) => {
  console.log(`selected ${value}`);
};
function AddProductForm() {
  const [name, setname] = useState("")
  const [price, setprice] = useState("")
  const [image, setimage] = useState("")
  const [viewImage, setViewImage] = useState([])
  const [category, setcategory] = useState("")
  const [selectedCategory, setSelectedCategory] = useState("");
  const dispatch = useDispatch();
  const products = useSelector((state) => state.product.products)
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formErrors, setFormErrors] = useState({});
  //

  const listFilter = [
    {
      name: 'Trạng thái',
      value: 'star'
    },
    {
      name: 'Ngày tạo',
      value: 'createdDate'
    }
  ]

  const { Search } = Input;
  const [isViewModal, setIsViewModal] = useState(false);
  const [viewHomestay, setViewHomestay] = useState({});

  const onSearch = (value, _e, info) => console.log(info?.source, value);

  const handleChange = (value) => {
    console.log(value); // Giá trị ID của mục được chọn
    setcategory(value);
  }
  const handleCancel = () => {
    setIsViewModal(false);
  }

  const showModal = (record) => {
    setIsViewModal(true);
    setViewHomestay(record);
    setViewImage(record.images)
    console.log(record);
  };
  //
  useEffect(() => {
    dispatch(getProducts());
  }, []);

  const columns = [
    {
      title: 'Tên homestay',
      dataIndex: 'name',
      key: 'name'
    },
    {
      title: 'Địa chỉ cụ thể',
      dataIndex: 'address',
      key: 'address'
    },
    {
      title: 'Trạng thái',
      dataIndex: 'status',
      key: 'status'
    },
    {
      title: 'Chủ homestay',
      dataIndex: 'ownerHomestay',
      key: 'ownerHomestay', 
      render: (data) => {
        return data.name; 
      }
    },
    {
      title: 'Giá thuê 1 đêm',
      dataIndex: 'price',
      key: 'price'
    },
    {
      title: 'Action',
      key: 'action',
      render: (_, record) => (
        <Space size="middle">
          <a onClick={() => showModal(record)} style={{ color: '#1677ff' }}><EyeOutlined /></a>
        </Space>
      ),
    },
  ];


  return (
    <div style={{ marginTop: '30px' }}>
      <Title level={2}>Quản trị homestay</Title>
      <Title level={4}>Danh mục</Title>
      <Row>
        <Form.Item label="Lọc theo" style={{ float: 'left' }}>
          <Select
            style={{ width: 143 }}
            onChange={handleChange}
            options={listFilter.map(filter => ({ value: filter.value, label: filter.name }))}
            defaultValue={listFilter[1].name}
          />
        </Form.Item>
        <Form.Item label="Tìm kiếm theo tên" style={{ float: 'left', marginLeft: ' 50px' }}>
          <Search
            placeholder="Tên homestay"
            allowClear
            size="medium"
            enterButton="search"
            onSearch={onSearch}
          />
        </Form.Item>
        <Form.Item label="Tìm kiếm theo tên chủ homestay" style={{ float: 'left', marginLeft: ' 50px' }}>
          <Search
            placeholder="Tên chủ homestay"
            allowClear
            size="medium"
            enterButton="search"
            onSearch={onSearch}
          />
        </Form.Item>
      </Row>
      {/* {loading ? <Spin className="example" size="large" /> : null}
      {error ? <p>Error: {error}</p> : null} */}
      <Table columns={columns} dataSource={products} />
      {/* popup form */}
    
      <Modal title={<div style={{ fontSize: '22px' }}>Xem thông tin chi tiết homstay</div>} open={isViewModal} onCancel={handleCancel}
        width={1100} style={{ fontSize: '40px' }}>
        <div style={{ fontSize: 18, fontWeight: 600 }}>
          <table>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Tên homestay </div> : {viewHomestay.name}</div><br /></td>
            </tr>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Giá            </div> : {viewHomestay.price} (VNĐ)</div><br /></td>
              <td style={{ width: 500 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Số lượng người </div> : {viewHomestay.numberPerson} (Người)</div><br /></td>
            </tr>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Số phòng</div> : {viewHomestay.roomNumber}</div><br /></td>
              <td style={{ width: 500 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Chính sách hủy phòng </div> : {viewHomestay.cancellationPolicy}</div><br /></td>
            </tr>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Thời gian nhận phòng </div> : {viewHomestay.timeCheckIn}</div><br /></td>
              <td style={{ width: 500 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Thời gian trả phòng </div> : {viewHomestay.timeCheckOut}</div><br /></td>
            </tr>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}>
                <div style={{ width: 200 }}>Ngày bắt đầu</div> : {moment(viewHomestay.createdDate).locale('vi').format('LL')}</div><br /></td>
              <td style={{ width: 500 }}><div style={{ display: 'flex' }}>
                <div style={{ width: 200 }}>Ngày kết thúc</div> : {moment(viewHomestay.endDate).locale('vi').format('LL')}
              </div><br /></td>
            </tr>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Tên homestay </div> : {viewHomestay.name}</div><br /></td>
              <td style={{ width: 500 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Mô tả          </div> : {viewHomestay.desc}</div><br /></td>
            </tr>
          </table>
          <div style={{ display: 'flex' }}><div style={{ width: 200 }}>Diện tích phòng</div> : {viewHomestay.acreage} (m2)</div><br />
          <div style={{ display: 'flex' }}><div style={{ width: 200 }}>Địa chỉ        </div> : {viewHomestay.address}</div><br />
          <div style={{ width: 1000 }}>Mô tả  : {viewHomestay.desc}</div><br />
          <div>Ảnh homstay :<br />
            <div style={{ width: 1030, padding: 20, flexWrap: 'wrap', borderRadius: 10, display: 'flex', justifyContent: 'center', border: '1px solid black' }}>
              {
                viewImage.map((img, index) => (
                  <Image
                    key={index}
                    src={img.imgUrl}
                    alt={`Homestay Image ${index}`}
                    style={{
                      maxWidth: '200px', // Đảm bảo ảnh không vượt quá chiều rộng của phần tử cha
                      margin: '0 10px 10px 0', // Thêm khoảng cách giữa các ảnh
                    }} preview={{
                      toolbarRender: (
                        _,
                        {
                          transform: { scale },
                          actions: { onFlipY, onFlipX, onRotateLeft, onRotateRight, onZoomOut, onZoomIn },
                        },
                      ) => (
                        <Space className="toolbar-wrapper">
                          <SwapOutlined rotate={90} onClick={onFlipY} />
                          <SwapOutlined onClick={onFlipX} />
                          <RotateLeftOutlined onClick={onRotateLeft} />
                          <RotateRightOutlined onClick={onRotateRight} />
                          <ZoomOutOutlined disabled={scale === 1} onClick={onZoomOut} />
                          <ZoomInOutlined disabled={scale === 50} onClick={onZoomIn} />
                        </Space>
                      ),
                    }} />
                ))
              }
            </div>
          </div>
        </div>
        </Modal>
    </div>
  )
}
export default AddProductForm

