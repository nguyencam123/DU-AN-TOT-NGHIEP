import React, { useEffect, useMemo, useState } from 'react'
import {
    Form,
    Select,
    Typography,
    Table,
    Row,
    Space,
    Modal, Tooltip, Button, Input, Col, DatePicker, message, Checkbox
} from 'antd';
import { useDispatch, useSelector } from 'react-redux'
import { EditOutlined, EyeOutlined, LoadingOutlined, ReloadOutlined, TeamOutlined } from '@ant-design/icons'
import moment from 'moment';
import Search from 'antd/es/input/Search';
import { addPromotion, fetchPromotion } from '../../../features/owner_homestay/getbooking/promotionThunk';
import * as Yup from 'yup'
import { fetchHomestay } from '../../../features/owner_homestay/homestayThunk';
import dayjs from 'dayjs';

const { Title } = Typography


const text = <span>Sửa khuyến mãi</span>;
const textupdate = <span>Dừng hoạt động khuyến mãi</span>;
const Promotion = () => {
    const [isAddFrom, setIsAddFrom] = useState(true);
    const [isLoading, setIsLoading] = useState(false);
    const [arrow, setArrow] = useState('Show');
    const dispatch = useDispatch();
    const userDetail = JSON.parse(localStorage.getItem('ownerDetail'));
    const UserID = userDetail?.data.id;
    useEffect(() => {
        dispatch(fetchPromotion(UserID));
        dispatch(fetchHomestay());
    }, []);
    const promotion = useSelector((state) => state.booking.promotions)
    const products = useSelector((state) => state.ownerHomestay.homestays);
    const [checkedValues, setCheckedValues] = useState([]);
    const onChangePromotion = (record) => (e) => {
        const { checked } = e.target;

        // Nếu checkbox được chọn, thêm ID vào danh sách
        // Nếu checkbox bị hủy chọn, loại bỏ ID khỏi danh sách
        setCheckedValues((prevSelectedIds) => {
            if (checked) {
                return [...prevSelectedIds, record.id];
            } else {
                return prevSelectedIds.filter((id) => id !== record.id);
            }
        });
    };
    /**
     * @returns Table colums
     */
    const columnsData = [
        {
            title: 'Tên homestay',
            dataIndex: 'name',
            key: 'name'
        },
        {
            title: 'Địa chỉ homestay',
            dataIndex: 'address',
            key: 'address'
        },
        {
            title: 'Giá homestay',
            dataIndex: 'price',
            key: 'price',
            align: 'center'
        },
        {
            title: 'Action',
            key: 'action',
            render: (_, record) => (
                <Space size="middle">
                    <Checkbox onChange={onChangePromotion(record)} />
                </Space>
            ),
        },
    ];
    /**
    * @access modal thêm 
    */
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [name, setName] = useState('');
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);

    const [homestay, setHomestay] = useState([])
    const [value, setValue] = useState(0);
    const [formErrors, setFormErrors] = useState({});
    const showModal = () => {
        setIsModalOpen(true);
        setName('');
        setStartDate(null);
        setEndDate(null);
        setValue(0);
        setCheckedValues([])
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
            title: 'Hành động',
            key: 'action',
            align: 'center',
            render: (_, record) => (
                <Space size="middle">
                    <Tooltip placement="top" title={text} arrow={mergedArrow}>
                        <a style={{ color: '#1677ff' }} onClick={() => handleEdit(record)}><EditOutlined /></a>
                    </Tooltip>
                    <Tooltip placement="top" title={textupdate} arrow={mergedArrow}>
                        <a style={{ color: '#1677ff' }}><ReloadOutlined /></a>
                    </Tooltip>
                </Space>
            ),
        },
    ];
    /**
     * Edit promotion
     */
    const handleEdit = (record) => {
        setIsAddFrom(false)
        setIsModalOpen(true);
        setName(record.name);
        const formattedDateStart = moment(record.startDate)
            .locale('vi')
            .format('YYYY-MM-DD');
        const formattedDateEnd = moment(record.endDate)
            .locale('vi')
            .format('YYYY-MM-DD');
        setStartDate(formattedDateStart)
        setEndDate(formattedDateEnd)
        setValue(record.value)
        console.log(record)
    }
    /**
     * @returns Add promotion
     */
    // console.log(moment(startDate).valueOf())
    const promotions = {
        name: name,
        startDate: moment(startDate).valueOf(),
        endDate: moment(endDate).valueOf(),
        type: 'TIEN',
        value: value,
        owner: UserID,
        homestay: checkedValues
    }
    const validationSchema = Yup.object().shape({
        name: Yup.string().required('Vui lòng nhập tên khuyến mãi'),
        value: Yup.number()
            .required('Vui lòng nhập số tiền giảm')
            .typeError('Vui lòng nhập số tiền giảm')
            .positive('Số tiền giảm phải là số dương'),
        // startDate: Yup.date().nullable().required('Vui lòng chọn ngày bắt đầu'),
        // endDate: Yup.date()
        //     .nullable()
        //     .required('Vui lòng chọn ngày kết thúc')
        //     .when(
        //         'startDate',
        //         (startDate, schema) => startDate && schema.min(startDate, 'Ngày kết thúc phải sau ngày bắt đầu')
        //     ),
    })

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await validationSchema.validate(promotions, { abortEarly: false });
            setIsLoading(true);
            if (isAddFrom) {
                await message.info('Đang tiến hành thêm bạn vui lòng đợi một vài giây nhé!');
                await dispatch(addPromotion(promotions));
                await dispatch(fetchPromotion(UserID));
                await setIsModalOpen(false);
                await setIsLoading(false)
                message.info('Thêm thành công');
                setName('')
                setValue(0)
                setStartDate(null)
                setEndDate(null)
            } else {
                await message.info('Đang tiến hành sửa bạn vui lòng đợi một vài giây nhé!');
                await dispatch(addPromotion(promotions));
                await dispatch(fetchPromotion(UserID));
                await setIsModalOpen(false);
                await setIsLoading(false)
                message.info('Sửa thành công');
            }
            dispatch(fetchPromotion());
            setFormErrors({});
        } catch (errors) {
            const errorObject = {};
            errors.inner.forEach((error) => {
                errorObject[error.path] = error.message;
            });
            setFormErrors(errorObject);
            setIsLoading(false);
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
            <Modal width={1200} title={isAddFrom == true ? 'Thêm khuyến mãi' : 'Sửa khuyến mãi'}
                okText={isAddFrom == true ? 'Thêm khuyến mãi' : 'Sửa khuyến mãi'}
                open={isModalOpen} onOk={handleSubmit} onCancel={handleCancel}
                okButtonProps={
                    isLoading
                        ? {
                            disabled: true,
                            icon: <LoadingOutlined />,
                            loading: true,
                        }
                        : {}
                }
                cancelButtonProps={
                    isLoading
                        ? {
                            disabled: true,
                        }
                        : {}
                }
            >
                <Form
                    name="basic"
                    labelCol={{
                        span: 14,
                    }}
                    wrapperCol={{
                        span: 24,
                    }}
                    style={{
                        maxWidth: 1200
                    }}
                    initialValues={{
                        remember: true,
                    }}
                    autoComplete="off"
                >
                    <div style={{ display: 'flex' }}>
                        <div>
                            <Row gutter={24} style={{ marginRight: 200 }}>
                                {/* Trường thứ nhất */}
                                <Col span={24}>
                                    <Form.Item
                                        label={<Title level={5} >Tên khuyến mãi</Title>}
                                        validateStatus={formErrors.name ? 'error' : ''} // Hiển thị lỗi cho trường name nếu có
                                        help={formErrors.name} // Hiển thị thông báo lỗi cho trường name nếu có
                                    >
                                        <Input style={{ width: '300px' }} value={name} onChange={(e) => setName(e.target.value)} />
                                    </Form.Item>
                                </Col>
                            </Row>
                            <Row gutter={24} style={{ marginRight: 200 }}>
                                {/* Trường thứ nhất */}
                                <Col span={24}>
                                    <Form.Item
                                        label={<Title level={5} >Ngày bắt đầu</Title>}
                                        validateStatus={formErrors.startDate ? 'error' : ''} // Hiển thị lỗi cho trường name nếu có
                                        help={formErrors.startDate} // Hiển thị thông báo lỗi cho trường name nếu có
                                    >
                                        <DatePicker style={{ width: '300px' }}
                                            value={startDate ? dayjs(startDate, 'YYYY-MM-DD') : null}
                                            onChange={(dates) => setStartDate(dates)} />
                                    </Form.Item>
                                </Col>
                            </Row>
                            <Row gutter={24} style={{ marginRight: 200 }}>
                                {/* Trường thứ nhất */}
                                <Col span={24}>
                                    <Form.Item
                                        label={<Title level={5}>Ngày kết thúc</Title>}
                                        validateStatus={formErrors.endDate ? 'error' : ''} // Hiển thị lỗi cho trường name nếu có
                                        help={formErrors.endDate} // Hiển thị thông báo lỗi cho trường name nếu có
                                    >
                                        <DatePicker style={{ width: '300px' }}
                                            value={endDate ? dayjs(endDate, 'YYYY-MM-DD') : null}
                                            onChange={(dates) => setEndDate(dates)} />
                                    </Form.Item>
                                </Col>
                            </Row>

                            <Row gutter={24} style={{ marginRight: 200 }}>
                                {/* Trường thứ nhất */}
                                <Col span={24}>
                                    <Form.Item
                                        label={<Title level={5}>Số tiền giảm</Title>}
                                        validateStatus={formErrors.value ? 'error' : ''} // Hiển thị lỗi cho trường name nếu có
                                        help={formErrors.value} // Hiển thị thông báo lỗi cho trường name nếu có
                                    >
                                        <Input addonAfter="VNĐ" value={value} style={{ width: '300px' }} onChange={(e) => setValue(e.target.value)} />
                                    </Form.Item>
                                </Col>
                            </Row>
                            <div style={{ color: 'red', marginLeft: 30 }}>*(lưu ý số tiền khi thêm sẽ được trừ thẳng vào giá homestay)</div>
                        </div>
                        <div>
                            <div style={{ color: 'red', marginLeft: 30 }}>*(Vui lòng chọn homestay mà bạn muốn áp dụng khuyến mãi)</div>
                            <Table columns={columnsData} dataSource={products} />
                        </div>
                    </div>
                </Form>
            </Modal>
        </div>
    )
}
export default Promotion;