import { Typography, DatePicker, Select, Input, Collapse, Button } from "antd"
import hotelimg from "../../../assets/img/saleHotel.png"
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


const text = <section>
    <div style={{ justifyContent: 'space-between', display: 'flex', fontSize: 18 }}>
        <div style={{ display: 'flex' }}><UserOutlined style={{ marginTop: 4 }} />&ensp;Người lớn</div><div><Input style={{ width: 40, height: 40 }} defaultValue={1} /></div>
    </div><br />
    <div style={{ justifyContent: 'space-between', display: 'flex', fontSize: 18 }}>
        <div style={{ display: 'flex' }}><WalletOutlined style={{ marginTop: 4 }} />&ensp;Trẻ em</div><div><Input style={{ width: 40, height: 40 }} defaultValue={1} /></div>
    </div><br />
    <div style={{ justifyContent: 'space-between', display: 'flex', fontSize: 18 }}>
        <div style={{ display: 'flex' }}><UsergroupAddOutlined style={{ marginTop: 4 }} />&ensp;Phòng</div><div><Input style={{ width: 40, height: 40 }} defaultValue={1} /></div>
    </div>
</section>;
const items = [
    {
        key: '1',
        label: 'Bạn hãy chọn số lượng người và phòng',
        children: <p>{text}</p>,
    }
];

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
        </>
    )
}
export default Hotel