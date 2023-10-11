import { Typography, DatePicker, Select, Input, Collapse, Button, Layout, Slider, Checkbox, Rate } from "antd"
import hotelimg from "../../../assets/img/saleHotel.png"
import logoTravel from "../../../assets/svg/Rectangle 405.svg"
import {
    SearchOutlined,
    FireOutlined,
    UserOutlined,
    WalletOutlined,
    UsergroupAddOutlined,
    PayCircleOutlined
} from "@ant-design/icons";
import {
    MDBInputGroup,
    MDBIcon
} from 'mdb-react-ui-kit';
import dayjs from 'dayjs';
import { useState } from "react";
import 'dayjs/locale/vi';
dayjs.locale('vi');
const { Title } = Typography
const { Header, Footer, Sider, Content } = Layout;


const text = <section>
    <div style={{ justifyContent: 'space-between', display: 'flex', fontSize: 18 }}>
        <div style={{ display: 'flex' }}><UserOutlined style={{ marginTop: 4 }} />&ensp;Người lớn</div><div><Input style={{ width: 40, height: 40 }} defaultValue={1} /></div>
    </div><br />
    <div style={{ justifyContent: 'space-between', display: 'flex', fontSize: 18 }}>
        <div style={{ display: 'flex' }}><WalletOutlined style={{ marginTop: 4 }} />&ensp;Trẻ em</div><div><Input style={{ width: 40, height: 40 }} defaultValue={1} /></div>
    </div>
</section>;
const utilities = <div style={{ width: '100%', height: '30%', backgroundColor: 'white', borderRadius: 10, padding: '5px 5px 5px' }}>
    <div>

    </div>
</div>
const items = [
    {
        key: '1',
        label: 'Bạn hãy chọn số lượng người và phòng',
        children: <p>{text}</p>,
    }
];
const itemsutilities = [
    {
        key: '2',
        label: 'Tiện nghi',
        children: <p>{utilities}</p>,
    }
]

const Hotel = () => {
    const onChange = (key) => {
        console.log(key);
    };
    const [checkInDate, setCheckInDate] = useState(null);
    const [numNights, setNumNights] = useState(1);

    const handleCheckInChange = (date) => {
        setCheckInDate(date);
    };

    const handleNumNightsChange = (value) => {
        setNumNights(value);
    };


    const disabledDate = (current) => {
        // Can not select days before today and today
        return current && current < dayjs().startOf('day');
    };
    const disabledDateTime = () => ({
        disabledHours: () => range(0, 24).splice(4, 20),
        disabledMinutes: () => range(30, 60),
        disabledSeconds: () => [55, 56],
    });
    const range = (start, end) => {
        const result = [];
        for (let i = start; i < end; i++) {
            result.push(i);
        }
        return result;
    };
    const options = []
    for (let i = 1; i < 30; i++) {
        options.push({
            value: i,
            label: i + ' đêm'
        }
        )
    }
    const calculateCheckOutDate = () => {
        if (checkInDate) {
            const checkOutDate = dayjs(checkInDate).add(numNights, 'day');
            return checkOutDate.format('dddd, DD [tháng] MM [năm] YYYY');
        }
        return '';
    };
    const [rangeValue, setRangeValue] = useState([0, 25000000]);
    const initialValue = [0, 25000000];
    const handleSliderChange = (value) => {
        // Kiểm tra nếu giá trị trái lớn hơn giá trị phải
        if (value[0] > value[1]) {
            return;
        }
        setRangeValue(value);
    };
    const handleInputChange = (index, value) => {
        const newValue = [...rangeValue];
        newValue[index] = value;

        // Kiểm tra nếu khoảng cách giữa giá trị trái và giá trị phải lớn hơn 20000
        if (newValue[1] - newValue[0] < 20000) {
            return;
        }

        setRangeValue(newValue);
    };
    const handleresetinput = () => {
        setRangeValue(initialValue);
    }
    return (
        <>
            <section style={{
                width: '100%', position: 'relative', height: 300,
                backgroundImage: 'linear-gradient(-180deg,rgba(0,160,255,1),#0770cd)', minHeight: 50, marginBottom: 400
            }}>
                <div style={{ justifyContent: 'center', alignItems: 'center', display: 'flex', bottom: 120, padding: '40px 100px 100px 0' }}>
                    <Title level={2} style={{ color: 'white' }}>Tìm & đặt phòng khách sạn giá rẻ
                        chỉ với <br />3 bước đơn giản!<br /><br />
                        <Title level={4} style={{ color: 'rgba(255,255,255,1)' }}>Khám phá ngay những ưu đãi tốt
                            nhất dành cho<br /> bạn tại TravelVIVU!</Title>
                    </Title>&emsp;&emsp;&emsp;&emsp;
                    <img src={hotelimg} style={{ borderRadius: 8, width: '30%' }} />
                </div>
                <div style={{ justifyContent: 'center', display: 'flex' }}>
                    <div style={{
                        position: 'absolute', width: 960,
                        height: 380, backgroundColor: 'white', bottom: -350,
                        borderRadius: 10, boxShadow: '0 0 3px 3px #ACAEB1', padding: '20px 20px 20px 20px'
                    }}>
                        <Title level={4} style={{ color: "#0194f3", display: 'flex' }} >
                            <SearchOutlined style={{ marginTop: 5, marginRight: 10 }} /> <div> Hãy tìm kiếm khách sạn quý khách mong muốn</div> </Title>
                        <hr />
                        <div><h5 style={{ fontSize: 16 }}>Thành phố đia điểm hoặc tên khách sạn</h5>
                            <MDBInputGroup className='mb-3' size='lg' noBorder textBefore={<MDBIcon fas icon='search' />}>
                                <input className='form-control' type='text' placeholder='Search' />
                            </MDBInputGroup>
                            <div>
                                <div style={{ display: 'flex' }}>
                                    <h5 style={{ fontSize: 16 }}>Nhận phòng:</h5>
                                    <h5 style={{ fontSize: 16, marginLeft: 260 }}>Số đêm:</h5>
                                    <h5 style={{ fontSize: 16, marginLeft: 260 }}>Trả phòng:</h5>
                                </div>
                                <div style={{ display: 'flex' }}>
                                    <DatePicker
                                        format="YYYY-MM-DD"
                                        disabledDate={disabledDate}
                                        disabledTime={disabledDateTime}
                                        size="lg"
                                        style={{ width: 300, height: 40 }}
                                        onChange={handleCheckInChange}
                                    />&emsp;&emsp;&emsp;
                                    <Select
                                        defaultValue="1 đêm"
                                        style={{ width: 300, minWidth: '50px', height: 40 }} // Thêm maxWidth ở đây
                                        size='large'
                                        options={options}
                                        suffixIcon={<FireOutlined style={{ fontSize: 18 }} />}
                                        onChange={handleNumNightsChange}
                                    />
                                    <div>
                                        <h5 style={{ fontSize: 18, marginLeft: 30, marginTop: 8 }}>{calculateCheckOutDate()}</h5>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <h5 style={{ fontSize: 16 }}>Khách vào phòng</h5>
                                <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                                    <Collapse items={items} onChange={onChange} style={{ width: '70%', height: 40 }} />
                                    <Button style={{ height: 40, width: '25%', fontSize: 18 }}><SearchOutlined />Tìm khách sạn</Button>
                                </div>
                            </div><br />
                            <div style={{ color: '#0194f3', fontSize: 18, display: 'flex', float: 'right' }}>
                                <PayCircleOutlined style={{ marginTop: 5 }} /> &ensp;Thanh toán khi nhận phòng
                            </div>
                        </div>
                    </div>
                </div>

            </section>
            <section style={{ padding: '20px 200px 400px 200px' }}>
                <Layout hasSider >
                    <Sider style={{ marginRight: 40, backgroundColor: '#f5f5f5' }}>
                        <img src={logoTravel} /><br /><br />
                        <div style={{ display: 'flex', width: '100%', height: '30%', backgroundColor: 'white', borderRadius: 10, padding: '5px 5px 5px' }}>
                            <div>
                                <div style={{ display: 'flex' }}>
                                    <Title level={2} style={{ fontSize: 16 }}>Phạm vi giá</Title>
                                    <Title level={2} style={{ fontSize: 16, marginLeft: 40, color: '#0194f3', cursor: 'pointer' }} onClick={handleresetinput}>Đặt lại</Title>
                                </div>
                                <Title level={2} style={{ fontSize: 12 }}>1 phòng 1 đêm</Title>
                                <div style={{ display: 'flex' }}>
                                    <Input style={{ width: 90, height: 20, fontSize: 10 }} value={rangeValue[0]}
                                        onChange={(e) => handleInputChange(0, e.target.value)} prefix='VND' />&ensp;
                                    <Input style={{ width: 90, height: 20, fontSize: 10 }} value={rangeValue[1]}
                                        onChange={(e) => handleInputChange(1, e.target.value)} prefix='VND' />
                                </div>
                                <Slider
                                    range={{
                                        draggableTrack: true,
                                    }}
                                    min={0}
                                    max={25000000}
                                    value={rangeValue}
                                    step={20000}
                                    onChange={handleSliderChange}
                                />
                            </div>
                        </div><br />
                        <div style={{ width: '100%', height: '45%', backgroundColor: 'white', borderRadius: 10, padding: '5px 5px 5px' }}>
                            <div>
                                <Title level={2} style={{ fontSize: 16 }}>Phạm vi giá</Title>
                                <Checkbox><Rate allowHalf disabled defaultValue={1} /></Checkbox>
                                <Checkbox><Rate allowHalf disabled defaultValue={2} /></Checkbox>
                                <Checkbox><Rate allowHalf disabled defaultValue={3} /></Checkbox>
                                <Checkbox><Rate allowHalf disabled defaultValue={4} /></Checkbox>
                                <Checkbox><Rate allowHalf disabled defaultValue={5} /></Checkbox>
                            </div>
                        </div><br />
                        <Collapse items={itemsutilities} size="small" defaultActiveKey={['2']} onChange={onChange} />
                    </Sider>
                    <Content style={{}}>Content</Content>
                </Layout>
            </section>
        </>
    )
}
export default Hotel