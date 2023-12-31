import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addProductAsync } from "../../features/product/createproductThunks";
import { fetchProducts } from "../../features/product/productThunk";
import { fetchCategory } from "../../features/category/categoryThunk"
import { useState } from "react";
import { Space, Table, Typography, Modal, Spin, Popconfirm, Form, Input, Row, Col, Select, Button } from 'antd';
import { QuestionCircleOutlined } from '@ant-design/icons';
import { removeProduct } from "../../features/product/deleteproductThunks";
import * as Yup from 'yup'

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
    const [category, setcategory] = useState("")
    const [selectedCategory, setSelectedCategory] = useState("");
    const dispatch = useDispatch();
    const loading = useSelector((state) => state.product.loading)
    const products = useSelector((state) => state.product.products)
    const error = useSelector((state) => state.product.error)
    const categorys = useSelector((state) => state.category.categorys)
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [formErrors, setFormErrors] = useState({});

    //

    const validationSchema = Yup.object().shape({
        name: Yup.string().required('Vui lòng nhập tên sản phẩm'),
        price: Yup.string()
            .matches(/^[0-9]+$/, 'Giá sản phẩm phải là số')
            .required('Vui lòng nhập giá sản phẩm'),
        image: Yup.string().required('Vui lòng chọn ảnh sản phẩm'),
    })

    const handleChange = (value) => {
        console.log(value); // Giá trị ID của mục được chọn
        setcategory(value);
    }

    const showModal = () => {
        setIsModalOpen(true);
    };
    const handleOk = () => {
        //setIsModalOpen(false);
        handleAddProduct()
    };
    const handleCancel = () => {
        setIsModalOpen(false);
    };
    //
    useEffect(() => {
        dispatch(fetchProducts());
    }, []);
    useEffect(() => {
        dispatch(fetchCategory());
    }, []);
    //
    //delete
    const handleDeleteProduct = (productId) => {
        dispatch(removeProduct(productId));
    }
    //add
    const handleAddProduct = () => {
        // Tạo object chứa dữ liệu từ form
        const productData = {
            name: name,
            price: price,
            category: {
                id: category
            },
            image: image
        };

        // Sử dụng Yup để validate dữ liệu
        validationSchema.validate(productData, { abortEarly: false })
            .then(() => {
                // Dữ liệu hợp lệ, dispatch action hoặc thực hiện các thao tác khác ở đây
                dispatch(addProductAsync(productData));

                // Xóa dữ liệu đã nhập trong form
                setname('');
                setprice('');
                setimage('');
                setFormErrors({});
                setIsModalOpen(false);
            })
            .catch(errors => {
                // Dữ liệu không hợp lệ, xử lý lỗi nếu cần
                const errorObject = {};
                errors.inner.forEach(error => {
                    errorObject[error.path] = error.message;
                });
                setFormErrors(errorObject);
            });
    };


    const columns = [
        {
            title: 'name',
            dataIndex: 'name',
            key: 'name'
        },
        {
            title: 'price',
            dataIndex: 'price',
            key: 'price',
        },
        {
            title: 'createdate',
            dataIndex: 'createdate',
            key: 'createdate',
        },
        {
            title: 'Action',
            key: 'action',
            render: (_, record) => (
                <Space size="middle">
                    <a onClick={showModal} style={{ color: '#1677ff' }}>Sửa</a>
                    <Popconfirm
                        title="Xóa mục này"
                        description="Bạn chắc chắn muốn xóa mục này chứ?"
                        icon={<QuestionCircleOutlined style={{ color: 'red' }} />}
                        cancelText="hủy"
                        okText="xóa"
                        onConfirm={() => handleDeleteProduct(record.id)} // Truyền record.id vào hàm handleDeleteProduct
                    >
                        <a style={{ color: '#1677ff' }}>Xóa</a>
                    </Popconfirm>
                </Space>
            ),
        },
    ];


    return (
        <div style={{ marginTop: '30px' }}>
            <Title level={2}>Quản trị sản phẩm</Title>
            <Title level={4}>Danh mục</Title>
            <Button type="primary" style={{ float: 'right', marginBottom: '20px' }} onClick={showModal}>Thêm</Button>
            {loading ? <Spin className="example" size="large" /> : null}
            {error ? <p>Error: {error}</p> : null}
            <Table columns={columns} dataSource={products} />
            {/* popup form */}
            <Modal title="Thêm sản phẩm" open={isModalOpen}
                onOk={handleOk} onCancel={handleCancel}
                cancelText="Hủy" okText="Thêm" width={800}>
                {/* <input
                    type="text"
                    value={productName}
                    onChange={(e) => setProductName(e.target.value)}
                    placeholder="Product Name"
                /> */}<br />
                <Form
                    name="basic"
                    labelCol={{
                        span: 8,
                    }}
                    wrapperCol={{
                        span: 24,
                    }}
                    style={{
                        maxWidth: 800,
                    }}
                    initialValues={{
                        remember: true,
                    }}
                    onFinish={onFinish}
                    onFinishFailed={onFinishFailed}
                    autoComplete="off"
                >
                    <Row gutter={24}>
                        {/* Trường thứ nhất */}
                        <Col span={12}>
                            <Form.Item
                                label="Name product"
                                name="name"
                                validateStatus={formErrors.name ? 'error' : ''} // Hiển thị lỗi cho trường name nếu có
                                help={formErrors.name} // Hiển thị thông báo lỗi cho trường name nếu có
                            >
                                <Input value={name}
                                    onChange={(e) => setname(e.target.value)} />
                            </Form.Item>
                        </Col>
                        {/* Trường thứ hai */}
                        <Col span={12}>
                            <Form.Item
                                label="Price"
                                name="price"
                                validateStatus={formErrors.price ? 'error' : ''} // Hiển thị lỗi cho trường price nếu có
                                help={formErrors.price} // Hiển thị thông báo lỗi cho trường price nếu có
                            >
                                <Input value={price}
                                    onChange={(e) => setprice(e.target.value)} />
                            </Form.Item>
                        </Col>
                        <Col span={12}>
                            <Form.Item
                                label="Category"
                                name="category"
                                rules={[
                                    {
                                        required: true,
                                        message: 'Please input your category!',
                                    },
                                ]}
                            >
                                <Select
                                    style={{ width: 243 }}
                                    onChange={handleChange}
                                    options={categorys.map(category => ({ value: category.id, label: category.name }))}
                                    value={category} // Đặt giá trị mặc định dựa trên selectedCategory (ID của danh mục)
                                    initialValue={categorys.length > 0 ? categorys[0].id : undefined}
                                />
                            </Form.Item>
                        </Col>
                    </Row>
                    <Row gutter={24}>
                        <Col span={12}>
                            <Form.Item
                                label="image"
                                name="image"
                                validateStatus={formErrors.image ? 'error' : ''} // Hiển thị lỗi cho trường image nếu có
                                help={formErrors.image} // Hiển thị thông báo lỗi cho trường image nếu có
                            >
                                <Input value={image}
                                    onChange={(e) => setimage(e.target.value)} />
                            </Form.Item>
                        </Col>
                    </Row>
                </Form>
            </Modal>
        </div>
    )
}
export default AddProductForm

