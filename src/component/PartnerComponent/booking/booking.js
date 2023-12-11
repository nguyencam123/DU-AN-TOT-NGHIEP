import React, { useEffect } from 'react'
import { PlusOutlined } from '@ant-design/icons';
import {
    Button,
    DatePicker,
    Form,
    Input,
    InputNumber,
    Select,
    Upload,
    Typography,
    Table,
    Row,
    Space
} from 'antd';
import { useDispatch, useSelector } from 'react-redux'
import { EyeOutlined } from '@ant-design/icons'
import { useState } from 'react'
import moment from 'moment';
import Search from 'antd/es/input/Search';
import { fetchBooking } from '../../../features/owner_homestay/getbooking/bookingThunk';
const { RangePicker } = DatePicker;
const { TextArea } = Input;
const { Title } = Typography



const Booking = () => {
    const dispatch = useDispatch();
    const userDetail = JSON.parse(localStorage.getItem('ownerDetail'));
    const UserID = userDetail?.data.id;
    useEffect(() => {
        dispatch(fetchBooking(UserID));
    }, []);
    const booking = useSelector((state) => state.booking.bookings)
    const columns = [
        {
            title: 'Tên tài khoản',
            dataIndex: 'user',
            key: 'user',
            render: (data) => {
                return data.name
            }
        },
        {
            title: 'Tên Homestay',
            dataIndex: 'homestay',
            key: 'homestayName',
            render: (data) => {
                return data.name
            }
        },
        {
            title: 'Ngày đặt',
            dataIndex: 'createdDate',
            key: 'createdDate',
            render: (data) => {
                return moment(data).locale('vi').format('LL')
            }
        },
        {
            title: 'Trạng thái',
            dataIndex: 'status',
            key: 'status',
            render: (data) => {
                if (data === 0) {
                    return 'Hủy'
                }
                if (data === 1) {
                    return 'Thành công'
                }
                if (data === 2) {
                    return 'Không thành công'
                }
            }
        },
        {
            title: 'Email người đặt',
            dataIndex: 'email',
            key: 'email'
        },
        {
            title: 'Tên người đặt',
            dataIndex: 'name',
            key: 'name'
        },
        {
            title: 'Số điện thoại người đặt',
            dataIndex: 'phoneNumber',
            key: 'createdDate'
        },
        {
            title: 'Tổng tiền',
            dataIndex: 'totalPrice',
            key: 'totalPrice'
        },
        {
            title: 'Action',
            key: 'action',
            render: (_, record) => (
                <Space size="middle">
                    <a style={{ color: '#1677ff' }}><EyeOutlined /></a>
                </Space>
            ),
        },
    ];
    const listFilter = [
        {
            name: 'Tất cả',
            value: ''
        },
        {
            name: 'Hủy',
            value: 0
        },
        {
            name: 'Thành công',
            value: 1
        },
        {
            name: 'Không thành công',
            value: 2
        }
    ]
    return (
        <div style={{ marginTop: '20px' }}>
            <Title level={2}>Quản trị đặt phòng</Title>
            <Title level={4}>Danh mục</Title>
            <Row>
                <Form.Item label="Trạng thái" style={{ float: 'left' }}>
                    <Select
                        style={{ width: 143 }}
                        options={listFilter.map(filter => ({ value: filter.value, label: filter.name }))}
                        defaultValue={listFilter[0].value}
                    />
                </Form.Item>
                <Form.Item label="Tìm kiếm theo tên" style={{ float: 'left', marginLeft: ' 50px' }}>
                    <Search
                        placeholder="Tên homestay"
                        allowClear
                        size="medium"
                        enterButton="search"
                    />
                </Form.Item>
                <Form.Item label="Tìm kiếm theo tên chủ homestay" style={{ float: 'left', marginLeft: ' 50px' }}>
                    <Search
                        placeholder="Tên chủ homestay"
                        allowClear
                        size="medium"
                        enterButton="search"
                    />
                </Form.Item>
            </Row>
            <Table columns={columns} dataSource={booking} />
        </div>
    )
}
export default Booking;