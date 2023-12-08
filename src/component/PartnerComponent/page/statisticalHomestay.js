import { DatePicker, Table, Tabs, Typography } from "antd"
import { Column } from '@ant-design/plots';
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import moment from 'moment';
import { fetchStatisticalByMonth, fetchStatisticalByYear } from "../../../features/owner_homestay/statistical/statisticalThunk";
import imgchart from '../../../assets/img/Tochartstatical.png';
import imgyellowchart from '../../../assets/img/yellowtoChart.png';
import imgBluechart from '../../../assets/img/blueimg.png';
import imgRedchart from '../../../assets/img/redimg.png';
import dayjs from 'dayjs';
import { fetchBooking } from "../../../features/owner_homestay/getbooking/bookingThunk";
const { Title } = Typography
const StatisticalHomestay = () => {
    const dispatch = useDispatch()
    const statistical = useSelector((state) => state.statistical.statisticalByYear)
    const statisticalByMonth = useSelector((state) => state.statistical.statisticalByMonth)
    const booking = useSelector((state) => state.booking.bookings)
    const userDetail = JSON.parse(localStorage.getItem('ownerDetail'));
    const UserID = userDetail?.data.id;

    const [year, setYear] = useState('2023')
    const onChange = (dateString) => {
        setYear(dateString)
        dispatch(fetchStatisticalByYear(UserID, dateString))
    };
    useEffect(() => {
        dispatch(fetchStatisticalByYear(UserID, year))
        dispatch(fetchStatisticalByMonth(UserID))
        dispatch(fetchBooking(UserID));
    }, []);

    const convertDataForChart = (data) => {
        if (!data || data.length === 0) {
            return [];
        }

        // Chuyển đổi dữ liệu thành định dạng phù hợp cho biểu đồ
        return data.map((item, index) => ({
            date: getMonthName(index + 1), // Hàm này sẽ cần được định nghĩa để trả về tên tháng dựa trên số thứ tự
            doanhSo: item.doanhSo,
            tongSoTien: item.tongSoTien,
            type: 'register', // Set a default type or determine it based on your requirements
        }));
    };


    // Hàm để lấy tên tháng dựa trên số thứ tự (1-12)
    const getMonthName = (monthNumber) => {
        const monthNames = [
            "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
        ];

        return monthNames[monthNumber - 1];
    };


    const convertedData = convertDataForChart(statistical);

    const formattedData = convertedData.map(item => ({
        date: item.date,
        tongSoTien: parseInt(item.tongSoTien),
    }));
    const config = {
        data: formattedData,
        xField: 'date',
        yField: 'tongSoTien',
        label: {
            // 可手动配置 label 数据标签位置
            position: 'middle',
            // 'top', 'bottom', 'middle',
            // 配置样式
            style: {
                fill: '#FFFFFF',
                opacity: 0.6,
            },
        },
        xAxis: {
            label: {
                autoHide: true,
                autoRotate: false,
            },
        },
        meta: {
            type: {
                alias: '类别',
            },
            sales: {
                alias: '销售额',
            },
        },
    };


    // table
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
        }
    ];
    const currentYear = new Date().getFullYear();

    return (
        <div>
            <Title style={{ marginTop: '20px' }}>Thống kê doanh thu</Title>
            <div>
                <div style={{ display: 'flex', justifyContent: 'center' }}>
                    <div style={{ width: '25%', height: 180, backgroundColor: '#321fdb', padding: 25, marginRight: 60, borderRadius: 8 }}>
                        <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={3}> 3.999.000 VNĐ</Title>
                        <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={5}> Doanh thu ngày hôm nay</Title>
                        <img src={imgchart} style={{ width: '100%' }} />
                    </div>
                    <div style={{ width: '25%', height: 180, backgroundColor: '#3399ff', padding: 25, marginRight: 60, borderRadius: 8 }}>
                        <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={3}> {statisticalByMonth.doanhSo} VNĐ</Title>
                        <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={5}> Doanh thu tháng này </Title>
                        <img src={imgBluechart} style={{ width: '100%' }} />
                    </div>
                    <div style={{ width: '25%', height: 180, backgroundColor: '#f9b115', padding: 25, marginRight: 60, borderRadius: 8 }}>
                        <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={3}> 3.999.000 VNĐ</Title>
                        <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={5}> Doanh thu năm nay </Title>
                        <img src={imgyellowchart} style={{ width: '100%', marginTop: 10 }} />
                    </div>
                    <div style={{ width: '25%', height: 180, backgroundColor: '#e55353', padding: 25, borderRadius: 8 }}>
                        <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={3}> 0 lượt</Title>
                        <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={5}>Số lượt đã đặt phòng trong năm</Title>
                        <img src={imgRedchart} style={{ width: '100%', marginTop: 1 }} />
                    </div>
                </div>
            </div>
            <div style={{ display: 'flex', marginTop: '20px' }}>
                <Title level={5} style={{ marginTop: '20px' }} >Biểu đồ thống kê doanh thu theo năm</Title>
                <DatePicker onChange={onChange} picker="year"
                    style={{ marginLeft: 'auto', width: 300, height: 40, marginTop: '20px' }}
                    disabledDate={(current) => current && current.year() > currentYear}
                    defaultValue={dayjs(`${currentYear}-01-01`)}
                />
            </div>
            <Column {...config} />
            <div style={{ marginTop: '40px' }}>
                <Table columns={columns} dataSource={booking} />
            </div>
        </div>
    )
}
export default StatisticalHomestay