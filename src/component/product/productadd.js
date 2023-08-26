import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addProductAsync } from "../../features/product/createproductThunks";
import { fetchProducts } from "../../features/product/productThunk";
import { useState } from "react";
import { Space, Table, Typography, Modal, Spin, Popconfirm } from 'antd';
import { QuestionCircleOutlined } from '@ant-design/icons';

const { Title } = Typography;


function AddProductForm() {

    const dispatch = useDispatch();
    const [productName, setProductName] = useState('');
    const loading = useSelector((state) => state.product.loading)
    const products = useSelector((state) => state.product.products)
    const error = useSelector((state) => state.product.error)
    const [isModalOpen, setIsModalOpen] = useState(false);
    //
    const showModal = () => {
        setIsModalOpen(true);
    };
    const handleOk = () => {
        setIsModalOpen(false);
    };
    const handleCancel = () => {
        setIsModalOpen(false);
    };
    //
    useEffect(() => {
        dispatch(fetchProducts());
    }, []);

    const handleAddProduct = () => {
        dispatch(addProductAsync({
            name: productName
        }))
        setProductName('');
    }

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
                    <a onClick={showModal}>Thêm</a>
                    <Popconfirm
                        title="Delete the task"
                        description="Are you sure to delete this task?"
                        icon={<QuestionCircleOutlined style={{ color: 'red' }} />}
                    >
                        <a>Delete</a>
                    </Popconfirm>
                </Space>
            ),
        },
    ];
    return (
        <div style={{ marginTop: '30px' }}>
            <Title level={2}>Quản trị sản phẩm</Title>
            <Title level={4}>Danh mục</Title>
            {loading ? <Spin className="example" size="large" /> : null}
            {error ? <p>Error: {error}</p> : null}
            <Table columns={columns} dataSource={products} />;
            <Modal title="Thêm sản phẩm" open={isModalOpen}
                onOk={handleOk} onCancel={handleCancel}
                cancelText="Hủy" okText="Thêm">
                <input
                    type="text"
                    value={productName}
                    onChange={(e) => setProductName(e.target.value)}
                    placeholder="Product Name"
                />
                <button onClick={handleAddProduct}>Add Product</button>
            </Modal>
        </div>
    )
}
export default AddProductForm