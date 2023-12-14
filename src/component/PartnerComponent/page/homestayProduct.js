import React, { useEffect } from 'react';
import {
  Space,
  Typography,
  Button,
  Table,
  Popconfirm,
  Modal,
  Form,
  Input,
  Row,
  Col,
  message,
  Image,
  TimePicker
} from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import {
  QuestionCircleOutlined,
  EditOutlined,
  DeleteOutlined,
  EyeOutlined,
  DownloadOutlined,
  SwapOutlined,
  RotateLeftOutlined,
  RotateRightOutlined,
  ZoomOutOutlined,
  ZoomInOutlined,
  ReloadOutlined,
  LoadingOutlined
} from '@ant-design/icons';
import { useState } from 'react';
import {
  EditHomestay,
  UpdateStatus,
  UpdateStatusToUpdating,
  addHomestay,
  fetchHomestay
} from '../../../features/owner_homestay/homestayThunk';
import { fetchConvenient } from '../../../features/owner_homestay/convenientThunk';
import { Checkbox, DatePicker, Select } from 'antd';
import moment from 'moment';
import 'moment/locale/vi';
import { fetchProvince } from '../../../features/owner_homestay/region/provinceThunk';
import axios from 'axios';
import * as Yup from 'yup';
import dayjs from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
dayjs.extend(customParseFormat);
const { RangePicker } = DatePicker;
const { TextArea } = Input;
const { Option } = Select;
const { Title } = Typography;

const HomeStayProduct = () => {
  const [checkedValues, setCheckedValues] = useState([]);


  const handleButtonClick = () => {
    setCheckedValues([]);
  };

  const handleChange = (values) => {
    setCheckedValues(values);
  };
  const [cities, setCities] = useState([]);
  const [selectedCity, setSelectedCity] = useState('');
  const [districts, setDistricts] = useState([]);
  const [selectedDistrict, setSelectedDistrict] = useState('');
  const [wards, setWards] = useState([]);
  const [selectedWard, setSelectedWard] = useState('');
  const dateFormat = 'YYYY/MM/DD';
  const isBeforeToday = (current) => {
    return current && current.isBefore(moment().startOf('day'));
  };
  const disabledEndDate = (current) => {
    // Kiểm tra xem ngày hiện tại có trước ngày bắt đầu không
    if (current < startDate) {
      return true;
    }
    return false; // Bỏ điều kiện không cho chọn ngày lớn hơn ngày hiện tại
  };

  useEffect(() => {
    dispatch(fetchHomestay());
    dispatch(fetchConvenient());
    // dispatch(fetchProvince());
  }, []);
  const dispatch = useDispatch();
  const products = useSelector((state) => state.ownerHomestay.homestays);
  const convenients = useSelector((state) => state.convenient.convenients);
  const onChangeConvenients = (checkedValues) => {
    setconvenient(checkedValues.join(','));
    setCheckedValues(checkedValues);
  };

  const [file, setFile] = useState([]);

  const handleFileChange = (e) => {
    let selectedFile = e.target.files;
    let fileList = [...selectedFile];
    setFile(fileList);
  };
  //modal
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isViewmodal, setIsviewmodal] = useState(false);
  const [isAddFrom, setIsAddForm] = useState(true);
  const showModalView = (record) => {
    setIsviewmodal(true);
    setname(record.name);
    setdesc(record.desc);
    setprice(record.price);
    setnumberPerson(record.numberPerson);
    setaddressView(record.address);
    setprice(record.price);
    setstartDate(record.startDate);
    setendDate(record.endDate);
    setIamge(record.images);
    settimeCheckIn(record.timeCheckIn);
    settimeCheckOut(record.timeCheckOut);
    setviewEditConvennient(record.detailHomestays);
    setcancellationPolicy(record.cancellationPolicy);
    setacreage(record.acreage);
    setroomNumber(record.roomNumber);
  };
  const showModal = () => {
    setIsModalOpen(true);
    setIsAddForm(true);
    setname('');
    setdesc('');
    setprice(null);
    setnumberPerson(null);
    // setaddress('')
    setprice(null);
    setcancellationPolicy(0);
    setacreage(0);
    setroomNumber(0);
    setstartDate(null);
    setendDate(null);
    settimeCheckIn(null);
    settimeCheckOut(null);
    setSelectedCity('');
    setSelectedDistrict('');
    setSelectedWard('');
    setCheckedValues([]);
  };
  const handleOk = () => {
    setIsModalOpen(false);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
    setIsviewmodal(false);
  };
  const columns = [
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
      title: 'Trạng thái của homestay',
      dataIndex: 'status',
      key: 'status',
      align: 'center',
      render(str) {
        if (str === 'CHO_DUYET') {
          return 'Chờ duyệt';
        } else if (str === 'HOAT_DONG') {
          return 'Hoạt động';
        } else {
          return 'Ngừng hoạt động';
        }
      }
    },
    {
      title: 'Action',
      key: 'action',
      render: (_, record) => (
        <Space size='middle'>
          <a onClick={() => showModalView(record)}>
            <EyeOutlined />
          </a>
          {record.status !== 'HOAT_DONG' && (
            <a onClick={() => handleEditRow(record)}>
              <EditOutlined />
            </a>
          )}
          {record.status === 'HOAT_DONG' && (
            <Popconfirm
              title='Xóa mục này'
              description='Bạn chắc chắn muốn cập nhật homestay thành không hoạt động không?'
              icon={<ReloadOutlined />}
              cancelText='Hủy'
              okText='Cập nhật'
              onConfirm={() => handleSubmitStatus(record)}
            >
              <a>
                <ReloadOutlined />
              </a>
            </Popconfirm>
          )}
          {record.status === 'KHONG_HOAT_DONG' && (
            <Popconfirm
              title='Xóa mục này'
              description='Bạn chắc chắn muốn cập nhật homestay thành chờ duyệt không?'
              icon={<ReloadOutlined />}
              cancelText='Hủy'
              okText='Cập nhật'
              onConfirm={() => handleSubmitStatusToUpdating(record)}
            >
              <a>
                <ReloadOutlined />
              </a>
            </Popconfirm>
          )}
        </Space>
      )
    }
  ];

  //

  //
  const [viewEditConvennient, setviewEditConvennient] = useState([]);
  const [convenientvir, setconvenient] = useState('');
  const [name, setname] = useState('');
  const [formErrors, setFormErrors] = useState({});
  const [acreage, setacreage] = useState(0);
  const [roomNumber, setroomNumber] = useState(0);
  const [cancellationPolicy, setcancellationPolicy] = useState(0);
  const [timeCheckIn, settimeCheckIn] = useState(null);

  const handleTimeChangestart = (time, timeString) => {
    settimeCheckIn(timeString);
  };
  const [timeCheckOut, settimeCheckOut] = useState(null);
  const handleTimeChangeend = (time, timeString) => {
    settimeCheckOut(timeString);
  };
  const [startDate, setstartDate] = useState(null);
  const handleDateChangestart = (dates) => {
    // setstartDate(moment(dates).valueOf());
    setstartDate(dates);
  };
  const [endDate, setendDate] = useState(null);
  const handleDateChangeend = (dates) => {
    // setendDate(moment(dates).valueOf());
    setendDate(dates);
  };
  const [desc, setdesc] = useState('');
  const [price, setprice] = useState(0);
  const [numberPerson, setnumberPerson] = useState(0);
  const [address, setaddress] = useState('');
  const [addressView, setaddressView] = useState('');
  const updateAddress = () => {
    const selectedCityName =
      cities.find((city) => city.Id === selectedCity)?.Name || '';
    const selectedDistrictName =
      districts.find((district) => district.Id === selectedDistrict)?.Name ||
      '';
    const selectedWardName =
      wards.find((ward) => ward.Id === selectedWard)?.Name || '';

    const newAddress = `${selectedWardName}, ${selectedDistrictName}, ${selectedCityName}`;
    setaddress(newAddress);
  };
  // Use useEffect to call updateAddress whenever the selectedCity, selectedDistrict, or selectedWard changes
  useEffect(() => {
    updateAddress();
  }, [selectedCity, selectedDistrict, selectedWard]);

  const [isLoading, setIsLoading] = useState(false);

  const [recordid, setRecordid] = useState('');
  const [image, setIamge] = useState([]);
  //getuserid
  const userDetail = JSON.parse(localStorage.getItem('ownerDetail'));
  const UserID = userDetail?.data.id;
  //
  const parsedAcreage = parseFloat(acreage).toFixed(2);
  const homestay = {
    name: name,
    timeCheckIn: timeCheckIn,
    timeCheckOut: timeCheckOut,
    cancellationPolicy: parseFloat(cancellationPolicy),
    startDate: moment(startDate).valueOf(),
    endDate: moment(endDate).valueOf(),
    desc: desc,
    price: parseFloat(price),
    numberPerson: parseInt(numberPerson),
    address: address,
    acreage: Number(parsedAcreage),
    ownerHomestay: UserID,
    roomNumber: parseInt(roomNumber)
  };
  //validateform
  const [errorFile, setErrorFile] = useState('')
  const validationSchema = Yup.object().shape({
    name: Yup.string().required('Vui lòng nhập tên sản phẩm'),
    price: Yup.number()
      .required('Vui lòng nhập giá sản phẩm')
      .typeError('Vui lòng nhập giá sản phẩm')
      .positive('Giá phải là số dương'),
    file: Yup.array()
      .min(5, 'Vui lòng chọn ít nhất 5 file')
      .max(20, 'Vui lòng chọn tối đa 20 file')
      .test('fileSize', 'File phải nhỏ hơn hoặc bằng 5MB', (value) => {
        if (!value) return true; // Allow empty array
        const maxSize = 5 * 1024 * 1024; // 5MB
        return value.every(file => file.size <= maxSize);
      }),
    numberPerson: Yup.number()
      .required('Vui lòng nhập số lượng người')
      .typeError('Vui lòng nhập số lượng người')
      .positive('Số lượng người phải là số dương'),
    address: Yup.string()
      .transform((value) => (value ? value.replace(/,/g, '') : value))
      .test('notEmpty', 'Vui lòng chọn đầy đủ địa chỉ của bạn', (value) => {
        return value && value.trim().length > 0;
      }),
    timeCheckIn: Yup.string().required('Vui lòng chọn thời gian nhận phòng'),
    timeCheckOut: Yup.string().required('Vui lòng chọn thời gian nhận phòng'),
    roomNumber: Yup.number()
      .required('Vui lòng nhập số phòng')
      .typeError('Vui lòng nhập số phòng')
      .positive('Số lượng phòng phải lớn hơn 0'),
    cancellationPolicy: Yup.number()
      .required('Vui lòng nhập số chính sách ủy phòng')
      .typeError('Vui lòng nhập số chính sách ủy phòng')
      .positive('Số lượng chính sách ủy phòng phải lớn hơn 0'),
    // province: Yup.string().required('Vui lòng chọn thành phố homestay'),
    startDate: Yup.number().required('Vui lòng chọn ngày bắt đầu'),
    endDate: Yup.number().required('Vui lòng chọn ngày kết thúc'),
    acreage: Yup.number()
      .required('Vui lòng nhập diện tích')
      .typeError('Vui lòng nhập diện tích')
      .positive('diện tích phòng phải lớn hơn 0'),
    desc: Yup.string().required('vui lòng nhập vào mô tả')
  });
  const handleSubmitStatus = async (record) => {
    await message.info(
      'Đang tiến hành sửa trạng thái bạn vui lòng đợi một vài giây nhé!'
    );
    await dispatch(UpdateStatus(record.id));
    dispatch(fetchHomestay());
  };
  const handleSubmitStatusToUpdating = async (record) => {
    await message.info(
      'Đang tiến hành sửa trạng thái bạn vui lòng đợi một vài giây nhé!'
    );
    await dispatch(UpdateStatusToUpdating(record.id));
    dispatch(fetchHomestay());
  };
  const handleSubmit = async (e) => {
    setIsLoading(true);
    e.preventDefault();
    try {
      await validationSchema.validate(homestay, { abortEarly: false });
      if (file.length < 5) {
        setErrorFile('Vui lòng chọn ít nhất 5 file')
        throw new Yup.ValidationError('Vui lòng chọn ít nhất 5 file', file, 'file');
      }
      if (file.length >= 20) {
        setErrorFile('Vui lòng chọn dưới 20 file')
        throw new Yup.ValidationError('Vui lòng chọn ít nhất 5 file', file, 'file');
      }
      const maxSize = 10 * 1024 * 1024; // 5MB
      if (!file.every(file => file.size <= maxSize)) {
        setErrorFile('File phải nhỏ hơn hoặc bằng 5MB')
        throw new Yup.ValidationError('File phải nhỏ hơn hoặc bằng 5MB', file, 'file');
      }
      if (isAddFrom) {
        await message.info(
          'Đang tiến hành thêm bạn vui lòng đợi một vài giây nhé!', 5
        );
        await dispatch(addHomestay(homestay, file, convenientvir));
        message.info('Thêm thành công');
        await setIsLoading(false);
        await setIsModalOpen(false);
        setname('');
        setprice(0);
        setdesc('');
        setnumberPerson(0);
        setcancellationPolicy(0);
        setnumberPerson(0);
        setacreage(0);
        setFile([]);
        setstartDate(null);
        setendDate(null);
        settimeCheckIn(null);
        settimeCheckOut(null);
        setSelectedCity('');
        setSelectedDistrict('');
        setSelectedWard('');
      } else {
        await message.info(
          'Đang tiến hành sửa bạn vui lòng đợi một vài giây nhé!', 5
        );
        await dispatch(EditHomestay(homestay, file, recordid, convenientvir));
        await setIsLoading(false);
        await setIsModalOpen(false);
        message.info('Sửa thành công');
      }
      dispatch(fetchHomestay());
      setFormErrors({});
      setErrorFile('')
    } catch (errors) {
      const errorObject = {};
      errors.inner.forEach((error) => {
        errorObject[error.path] = error.message;
      });

      setFormErrors(errorObject);
      setIsLoading(false)
    }
  };

  const handleEditRow = (record) => {
    setIsModalOpen(true);
    setIsAddForm(false);
    setRecordid(record.id);
    setname(record.name);
    setdesc(record.desc);
    setprice(record.price);
    setnumberPerson(record.numberPerson);
    setaddress(record.address);
    setprice(record.price);
    const formattedDateStart = moment(record.startDate)
      .locale('vi')
      .format('YYYY-MM-DD');
    const formattedDateEnd = moment(record.endDate)
      .locale('vi')
      .format('YYYY-MM-DD');

    setstartDate(formattedDateStart);
    setendDate(formattedDateEnd);
    setIamge(record.images);
    setcancellationPolicy(record.cancellationPolicy);
    setroomNumber(record.numberPerson);
    setacreage(record.acreage);
    settimeCheckIn(record.timeCheckIn);
    settimeCheckOut(record.timeCheckOut);
    setconvenient(record.detailHomestays);
    console.log(record)
    //
    setFormErrors({});

    const addressParts = record.address.split(', ');
    const selectedWardName = addressParts[0]; // Lấy tên phường/xã từ địa chỉ
    const selectedDistrictName = addressParts[1]; // Lấy tên quận/huyện từ địa chỉ
    const selectedCityName = addressParts[2];
    const selectedCityData = cities.find(
      (city) => city.Name === selectedCityName
    );
    if (selectedCityData) {
      setSelectedCity(selectedCityData.Id);
    }

    if (selectedCityData && selectedDistrictName) {
      const selectedDistrictData = selectedCityData.Districts.find(
        (district) => district.Name === selectedDistrictName
      );
      if (selectedDistrictData) {
        setDistricts(selectedCityData.Districts);
        setSelectedDistrict(selectedDistrictData.Id);
      }
    }

    if (selectedDistrictName && selectedWardName) {
      const selectedDistrictData = districts.find(
        (district) => district.Name === selectedDistrictName
      );
      if (selectedDistrictData) {
        setWards(selectedDistrictData.Wards);
        const selectedWardData = selectedDistrictData.Wards.find(
          (ward) => ward.Name === selectedWardName
        );
        if (selectedWardData) {
          setSelectedWard(selectedWardData.Id);
        }
      }
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          'https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json'
        );
        setCities(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    if (selectedCity) {
      const selectedCityData = cities.find((city) => city.Id === selectedCity);
      if (selectedCityData) {
        setDistricts(selectedCityData.Districts);
      }
    }
  }, [selectedCity, cities]);

  useEffect(() => {
    if (selectedDistrict) {
      const selectedDistrictData = districts.find(
        (district) => district.Id === selectedDistrict
      );
      if (selectedDistrictData) {
        setWards(selectedDistrictData.Wards);
      }
    }
  }, [selectedDistrict, districts]);

  return (
    <>
      <Title style={{ marginTop: '20px' }}>Quản lý Homestay</Title>
      <div style={{ marginBottom: 20, float: 'right' }}>
        <Button type='primary' onClick={showModal}>
          Thêm mới HomeStay
        </Button>
      </div>
      <Modal
        title={
          isAddFrom == true ? (
            <div style={{ fontSize: 24 }}>Thêm homstay </div>
          ) : (
            <div style={{ fontSize: 24 }}>Sửa homestay</div>
          )
        }
        open={isModalOpen}
        onCancel={handleCancel}
        width={900}
        okText={isAddFrom == true ? 'Thêm homstay' : 'Sửa homestay'}
        cancelText='Hủy'
        onOk={handleSubmit}
        maskClosable={false}
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
          name='basic'
          labelCol={{
            span: 8
          }}
          wrapperCol={{
            span: 24
          }}
          style={{
            maxWidth: 800
          }}
          initialValues={{
            remember: true
          }}
          autoComplete='off'
        >
          <Row gutter={24} style={{ marginLeft: 1 }}>
            {/* Trường thứ nhất */}
            <Col span={12}>
              <Form.Item
                label={<Title level={5}>Tên homestay</Title>}
                validateStatus={formErrors.name ? 'error' : ''} // Hiển thị lỗi cho trường name nếu có
                help={formErrors.name} // Hiển thị thông báo lỗi cho trường name nếu có
              >
                <Input value={name} onChange={(e) => setname(e.target.value)} />
              </Form.Item>
            </Col>
            {/* Trường thứ hai */}
            <Col span={12}>
              <Form.Item
                label={<Title level={5}>Giá homestay</Title>}
                validateStatus={formErrors.price ? 'error' : ''}
                help={formErrors.price}
              >
                <Input
                  value={price}
                  onChange={(e) => setprice(e.target.value)}
                  addonAfter='VNĐ'
                />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={24} style={{ marginLeft: 3 }}>
            <Col span={12}>
              <Form.Item
                label={<Title level={5}>Số người ở</Title>}
                validateStatus={formErrors.numberPerson ? 'error' : ''}
                help={formErrors.numberPerson}
              >
                <Input
                  value={numberPerson}
                  onChange={(e) => setnumberPerson(e.target.value)}
                />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                label={<Title level={5}>Diện tích</Title>}
                validateStatus={formErrors.acreage ? 'error' : ''}
                help={formErrors.acreage}
              >
                <Input
                  value={acreage}
                  onChange={(e) => setacreage(e.target.value)}
                  addonAfter='m2'
                />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={24} style={{ marginLeft: 4 }}>
            <Col span={12}>
              <Form.Item label={<Title level={5}>Ngày bắt đầu</Title>}>
                <DatePicker
                  onChange={handleDateChangestart}
                  style={{ width: '100%' }}
                  value={startDate ? dayjs(startDate, 'YYYY-MM-DD') : null}
                  disabledDate={isBeforeToday}
                  allowClear={true}
                />
                <div style={{ color: 'red' }}>{formErrors.startDate}</div>
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item label={<Title level={5}>Ngày kết thúc</Title>}>
                <DatePicker
                  onChange={handleDateChangeend}
                  style={{ width: '100%' }}
                  value={endDate ? dayjs(endDate, 'YYYY-MM-DD') : null}
                  disabledDate={isBeforeToday}
                  allowClear={true}
                />
                <div style={{ color: 'red' }}>{formErrors.endDate}</div>
              </Form.Item>
            </Col>
          </Row>

          {/* time bắt đầu */}
          <Row gutter={24} style={{ marginLeft: 4 }}>
            <Col span={12}>
              <Title level={5}>Thời gian nhận phòng:</Title>
              <TimePicker
                onChange={handleTimeChangestart}
                value={timeCheckIn && dayjs(timeCheckIn, 'HH:mm:ss')} // Fix here
                style={{ width: '67%', float: 'right' }}
                allowClear={true}
              />
              <div style={{ color: 'red' }}>{formErrors.timeCheckIn}</div>
            </Col>
            <Col span={12}>
              <Title level={5}>Thời gian trả phòng:</Title>
              <TimePicker
                onChange={handleTimeChangeend}
                value={timeCheckOut && dayjs(timeCheckOut, 'HH:mm:ss')} // Fix here
                style={{ width: '67%', float: 'right' }}
                allowClear={true}
              />
              <div style={{ color: 'red' }}>{formErrors.timeCheckOut}</div>
            </Col>
          </Row>
          <Row gutter={24} style={{ marginLeft: 4, marginTop: 20 }}>
            <Col span={12}>
              <Title level={5}>Chính sách hủy phòng:</Title>
              <Input
                style={{ width: '67%', float: 'right' }}
                value={cancellationPolicy}
                onChange={(e) => setcancellationPolicy(e.target.value)}
              />
              <div style={{ color: 'red', marginTop: 35 }}>
                {formErrors.cancellationPolicy}
              </div>
            </Col>
            <Col span={12}>
              <Title level={5}>Số phòng:</Title>
              <Input
                style={{ width: '67%', float: 'right' }}
                value={roomNumber}
                onChange={(e) => setroomNumber(e.target.value)}
              />
              <div style={{ color: 'red', marginTop: 35 }}>
                {formErrors.roomNumber}
              </div>
            </Col>
          </Row>
          <Row
            gutter={24}
            style={{ marginTop: 10, marginBottom: 20, marginLeft: 20 }}
          >
            {/* <DatePicker /> */}
            <div style={{ display: 'flex' }}>
              <div style={{ marginRight: 30, marginLeft: 20 }}>
                <Title level={5}>Tỉnh/Thành phố:</Title>
                <select
                  value={selectedCity}
                  onChange={(e) => setSelectedCity(e.target.value)}
                  style={{ borderRadius: 5, height: 30, width: 200 }}
                >
                  <option value=''>Chọn tỉnh/thành phố</option>
                  {cities.map((city) => (
                    <option key={city.Id} value={city.Id}>
                      {city.Name}
                    </option>
                  ))}
                </select>
                <div style={{ color: 'red' }}>{formErrors.selectedCity}</div>
              </div>
              <div style={{ marginRight: 30 }}>
                <Title level={5}>Quận/Huyện:</Title>
                <select
                  value={selectedDistrict}
                  onChange={(e) => setSelectedDistrict(e.target.value)}
                  style={{ borderRadius: 5, height: 30, width: 200 }}
                >
                  <option value=''>Chọn quận/huyện</option>
                  {districts.map((district) => (
                    <option key={district.Id} value={district.Id}>
                      {district.Name}
                    </option>
                  ))}
                </select>
                <div style={{ color: 'red' }}>
                  {formErrors.selectedDistrict}
                </div>
              </div>
              <div>
                <Title level={5}>Phường/Xã:</Title>
                <select
                  value={selectedWard}
                  onChange={(e) => setSelectedWard(e.target.value)}
                  style={{ borderRadius: 5, height: 30, width: 200 }}
                >
                  <option value=''>Chọn phường/xã</option>
                  {wards.map((ward) => (
                    <option key={ward.Id} value={ward.Id}>
                      {ward.Name}
                    </option>
                  ))}
                </select>
                <div style={{ color: 'red' }}>{formErrors.selectedWard}</div>
              </div>
            </div>
          </Row>
          <Row gutter={24}>
            <Col span={24}>
              <Title level={5}>Tiện ích homestay</Title>
              <div>
                <Checkbox.Group
                  options={convenients.map((item) => ({
                    label: item.name,
                    value: item.id,
                  }))}
                  value={checkedValues}
                  onChange={onChangeConvenients}
                />
              </div>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={24}>
              <Title level={5}>Mô tả homestay</Title>
              <TextArea
                style={{ width: '900px' }}
                value={desc}
                onChange={(e) => setdesc(e.target.value)}
              />
            </Col>
            {/* <Col span={12}>
              <Title level={5}>Ngày kết thúc</Title>
              <DatePicker onChange={handleDateChangeend} />
            </Col> */}
          </Row>
        </Form>
        <form onSubmit={handleSubmit} style={{ marginTop: 20 }}>
          <div>

            <label htmlFor='image'>Chọn ảnh(Chọn ít nhất 5 ảnh và tối đa 20 ảnh)<br /></label>
            <input
              type='file'
              id='image'
              multiple
              accept='image/*'
              onChange={handleFileChange}
            />
            {isAddFrom == true ? (
              ''
            ) : (
              <div style={{ color: 'red' }}>
                *(lưu ý khi chỉnh sửa chúng tôi sẽ chèn vào ảnh cũ của bạn)
              </div>
            )}
          </div>
          <div style={{ color: 'red' }}>{errorFile}</div>
        </form>
        {isAddFrom == true ? (
          ''
        ) : (
          <div style={{ marginTop: 10 }}>
            {image.map((imageurl, index) => (
              <Image
                key={index}
                src={imageurl.imgUrl}
                alt={`Homestay Image ${index}`}
                style={{
                  maxWidth: '200px', // Đảm bảo ảnh không vượt quá chiều rộng của phần tử cha
                  margin: '0 10px 10px 0' // Thêm khoảng cách giữa các ảnh
                }}
                preview={{
                  toolbarRender: (
                    _,
                    {
                      transform: { scale },
                      actions: {
                        onFlipY,
                        onFlipX,
                        onRotateLeft,
                        onRotateRight,
                        onZoomOut,
                        onZoomIn
                      }
                    }
                  ) => (
                    <Space className='toolbar-wrapper'>
                      <SwapOutlined rotate={90} onClick={onFlipY} />
                      <SwapOutlined onClick={onFlipX} />
                      <RotateLeftOutlined onClick={onRotateLeft} />
                      <RotateRightOutlined onClick={onRotateRight} />
                      <ZoomOutOutlined
                        disabled={scale === 1}
                        onClick={onZoomOut}
                      />
                      <ZoomInOutlined
                        disabled={scale === 50}
                        onClick={onZoomIn}
                      />
                    </Space>
                  )
                }}
              />
            ))}
          </div>
        )}
      </Modal>
      <Modal
        title={
          <div style={{ fontSize: '22px' }}>Xem thông tin chi tiết homstay</div>
        }
        open={isViewmodal}
        onCancel={handleCancel}
        onOk={handleCancel}
        width={1100}
        style={{ fontSize: '40px' }}
      >
        <div style={{ fontSize: 18, fontWeight: 600 }}>
          <table>
            <tr>
              <td style={{ width: 600 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Tên homestay </div> : {name}
                </div>
                <br />
              </td>
              <td style={{ width: 500 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Diện tích phòng</div> : {acreage}{' '}
                  (m2)
                </div>
                <br />
              </td>
            </tr>
            <tr>
              <td style={{ width: 600 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Giá </div> : {price} (VNĐ)
                </div>
                <br />
              </td>
              <td style={{ width: 500 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Số lượng người </div> :{' '}
                  {numberPerson} (Người)
                </div>
                <br />
              </td>
            </tr>
            <tr>
              <td style={{ width: 600 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Số phòng</div> : {roomNumber}
                </div>
                <br />
              </td>
              <td style={{ width: 500 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Chính sách hủy phòng </div> :{' '}
                  {cancellationPolicy}
                </div>
                <br />
              </td>
            </tr>
            <tr>
              <td style={{ width: 600 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Thời gian nhận phòng </div> :{' '}
                  {timeCheckIn}
                </div>
                <br />
              </td>
              <td style={{ width: 500 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Thời gian trả phòng </div> :{' '}
                  {timeCheckOut}
                </div>
                <br />
              </td>
            </tr>
            <tr>
              <td style={{ width: 600 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Ngày bắt đầu</div> :{' '}
                  {moment(startDate).locale('vi').format('LL')}
                </div>
                <br />
              </td>
              <td style={{ width: 500 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Ngày kết thúc</div> :{' '}
                  {moment(endDate).locale('vi').format('LL')}
                </div>
                <br />
              </td>
            </tr>
          </table>
          <div style={{ display: 'flex' }}>
            <div style={{ width: 200 }}>Tiện ích </div> :{' '}
            {viewEditConvennient.map((items) => (
              <div> {items.convenientHomestay?.name},</div>
            ))}
          </div>
          <br />
          <div style={{ display: 'flex' }}>
            <div style={{ width: 200 }}>Mô tả </div> : {desc}
          </div>
          <br />
          <div style={{ display: 'flex' }}>
            <div style={{ width: 200 }}>Địa chỉ </div> : {addressView}
          </div>
          <br />
          <div>
            Ảnh homstay :<br />
            <div
              style={{
                width: 1030,
                padding: 20,
                flexWrap: 'wrap',
                borderRadius: 10,
                display: 'flex',
                justifyContent: 'center',
                border: '1px solid black'
              }}
            >
              {image.map((imageurl, index) => (
                <Image
                  key={index}
                  src={imageurl.imgUrl}
                  alt={`Homestay Image ${index}`}
                  style={{
                    maxWidth: '200px', // Đảm bảo ảnh không vượt quá chiều rộng của phần tử cha
                    margin: '0 10px 10px 0' // Thêm khoảng cách giữa các ảnh
                  }}
                  preview={{
                    toolbarRender: (
                      _,
                      {
                        transform: { scale },
                        actions: {
                          onFlipY,
                          onFlipX,
                          onRotateLeft,
                          onRotateRight,
                          onZoomOut,
                          onZoomIn
                        }
                      }
                    ) => (
                      <Space className='toolbar-wrapper'>
                        <SwapOutlined rotate={90} onClick={onFlipY} />
                        <SwapOutlined onClick={onFlipX} />
                        <RotateLeftOutlined onClick={onRotateLeft} />
                        <RotateRightOutlined onClick={onRotateRight} />
                        <ZoomOutOutlined
                          disabled={scale === 1}
                          onClick={onZoomOut}
                        />
                        <ZoomInOutlined
                          disabled={scale === 50}
                          onClick={onZoomIn}
                        />
                      </Space>
                    )
                  }}
                />
              ))}
            </div>
          </div>
        </div>
      </Modal>
      <Table columns={columns} dataSource={products} rowKey='key' />
    </>
  );
};
export default HomeStayProduct;
