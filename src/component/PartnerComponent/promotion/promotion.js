import React, { useEffect, useMemo, useState } from 'react'
import {
    Form,
    Select,
    Typography,
    Table,
    Row,
    Space,
    Modal, Tooltip, Button, Input, Col, DatePicker, message
} from 'antd';
import { useDispatch, useSelector } from 'react-redux'
import { EyeOutlined, TeamOutlined } from '@ant-design/icons'
import moment from 'moment';
import Search from 'antd/es/input/Search';
import { addPromotion, fetchPromotion } from '../../../features/owner_homestay/getbooking/promotionThunk';
import * as Yup from 'yup'

const { Title } = Typography


const text = <span>Gán khuyến mãi vào homestay</span>;
const Promotion = () => {
    const isAddFrom = true
    const [arrow, setArrow] = useState('Show');
    const dispatch = useDispatch();
    const userDetail = JSON.parse(localStorage.getItem('ownerDetail'));
    const UserID = userDetail?.data.id;
    useEffect(() => {
        dispatch(fetchPromotion(UserID));
    }, []);
    const promotion = useSelector((state) => state.booking.promotions)
    /**
    * @access modal thêm 
    */
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [namePromotion, setNamePromotion] = useState('');
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [value, setValue] = useState(0);
    const [formErrors, setFormErrors] = useState({});
    const showModal = () => {
        setIsModalOpen(true);
    };
    const handleOk = () => {
        setIsModalOpen(false);
    };
    const handleCancel = () => {
        setIsModalOpen(false);
    };
    /**
     * Table promotion
     */
    const mergedArrow = useMemo(() => {
        if (arrow === 'Hide') {
            return false;
        }
        if (arrow === 'Show') {
            return true;
        }
        return {
            pointAtCenter: true,
        };
    }, [arrow]);
    const columns = [
        {
            title: 'Tên khuyến mãi',
            dataIndex: 'name',
            key: 'name',
            render: (data) => {
                return data
            }
        },
        {
            title: 'Ngày bắt đầu',
            dataIndex: 'startDate',
            key: 'startDate',
            align: 'center',
            render: (data) => {
                return moment(data).locale('vi').format('LL')
            }
        },
        {
            title: 'Ngày kết thúc',
            dataIndex: 'endDate',
            align: 'center',
            key: 'endDate',
            render: (data) => {
                return moment(data).locale('vi').format('LL')
            }
        },
        {
            title: 'Số tiền giảm',
            dataIndex: 'value',
            key: 'value',
            align: 'center',
        },
        {
            title: 'Action',
            key: 'action',
            align: 'center',
            render: (_, record) => (
                <Space size="middle">
                    <Tooltip placement="top" title={text} arrow={mergedArrow}>
                        <a style={{ color: '#1677ff' }}><TeamOutlined /></a>
                    </Tooltip>
                </Space>
            ),
        },
    ];
    /**
     * @returns Add promotion
     */
    const promotions = {
        name: namePromotion,
        startDate: startDate,
        endDate: endDate,
        type: 'TIEN',
        value: value
    }
    const validationSchema = Yup.object().shape({
        name: Yup.string().required('Vui lòng nhập tên sản phẩm'),
        value: Yup.number()
            .required('Vui lòng nhập giá sản phẩm')
            .typeError('Vui lòng nhập giá sản phẩm')
            .positive('Giá phải là số dương'),

    })
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await validationSchema.validate(promotions, { abortEarly: false });

            if (isAddFrom) {
                await setIsModalOpen(false);
                await message.info('Đang tiến hành thêm bạn vui lòng đợi một vài giây nhé!');
                await dispatch(addPromotion(promotions));
                message.info('Thêm thành công');
                setNamePromotion('')
                setValue(0)
                setStartDate(null)
                setEndDate(null)
            } else {
                await setIsModalOpen(false);
                await message.info('Đang tiến hành sửa bạn vui lòng đợi một vài giây nhé!');
                await dispatch(addPromotion(promotions));
                message.info('Sửa thành công');
            }
            dispatch(fetchPromotion());
            setFormErrors({});
        } catch (errors) {
            const errorObject = {};
            errors.inner.forEach(error => {
                errorObject[error.path] = error.message;
            });
            setFormErrors(errorObject);
        }
    };
    return (
        <div style={{ marginTop: '20px' }}>
            <Title level={2}>Quản trị khuyến mãi</Title>
            <div style={{ marginBottom: 20, float: 'right' }}>
                <Button type="primary" onClick={showModal}>
                    Thêm mới khuyến mãi
                </Button>
            </div>
            <Title level={4}>Danh mục</Title>
            <br />
            <Row style={{ float: 'right' }}>
                {/* <Form.Item label="Trạng thái" style={{ float: 'left' }}>
                    <Select
                        style={{ width: 143 }}
                        options={listFilter.map(filter => ({ value: filter.value, label: filter.name }))}
                        defaultValue={listFilter[0].value}
                    />
                </Form.Item> */}
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
            <Table columns={columns} dataSource={promotion} />
            <Modal title={isAddFrom == true ? 'Thêm khuyến mãi' : 'Sửa khuyến mãi'} open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
                <Form
                    name="basic"
                    labelCol={{
                        span: 14,
                    }}
                    wrapperCol={{
                        span: 24,
                    }}
                    style={{
                        maxWidth: 800
                    }}
                    initialValues={{
                        remember: true,
                    }}
                    autoComplete="off"
                >
                    <Row gutter={24} style={{ marginRight: 200 }}>
                        {/* Trường thứ nhất */}
                        <Col span={24}>
                            <Form.Item
                                label={<Title level={5} >Tên khuyến mãi</Title>}

                            >
                                <Input style={{ width: '300px' }} onChange={(e) => setNamePromotion(e.target.value)} />
                            </Form.Item>
                        </Col>
                    </Row>
                    <Row gutter={24} style={{ marginRight: 200 }}>
                        {/* Trường thứ nhất */}
                        <Col span={24}>
                            <Form.Item
                                label={<Title level={5} >Ngày bắt đầu</Title>}

                            >
                                <DatePicker style={{ width: '300px' }} onChange={(dates) => setStartDate(dates)} />
                            </Form.Item>
                        </Col>
                    </Row>
                    <Row gutter={24} style={{ marginRight: 200 }}>
                        {/* Trường thứ nhất */}
                        <Col span={24}>
                            <Form.Item
                                label={<Title level={5}>Ngày kết thúc</Title>}

                            >
                                <DatePicker style={{ width: '300px' }} onChange={(dates) => setEndDate(dates)} />
                            </Form.Item>
                        </Col>
                    </Row>
                    <div style={{ color: 'red' }}>*(lưu ý số tiền khi thêm sẽ được trừ thẳng vào giá homestay)</div>
                    <Row gutter={24} style={{ marginRight: 200 }}>
                        {/* Trường thứ nhất */}
                        <Col span={24}>
                            <Form.Item
                                label={<Title level={5}>Số tiền giảm</Title>}

                            >
                                <Input addonAfter="VNĐ" style={{ width: '300px' }} onChange={(e) => setValue(e.target.value)} />
                            </Form.Item>
                        </Col>
                    </Row>
                </Form>
            </Modal>
        </div>
    )
}
export default Promotion;