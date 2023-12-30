import { DatePicker, Table, Tabs, Typography } from "antd"
import { Column } from '@ant-design/plots';
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import moment from 'moment';
import { fetchStatisticalByDay, fetchStatisticalByMonth, fetchStatisticalByTop5, fetchStatisticalByYear, fetchStatisticalByYears } from "../../../features/admin/statistical/statisticalThunk";
import imgchart from '../../../assets/img/Tochartstatical.png';
import imgyellowchart from '../../../assets/img/yellowtoChart.png';
import imgBluechart from '../../../assets/img/blueimg.png';
import imgRedchart from '../../../assets/img/redimg.png';
import dayjs from 'dayjs';
const { Title } = Typography
const Statistical = () => {
  const dispatch = useDispatch()
  const statistical = useSelector((state) => state.statisticalAdmin.statisticalByYear)
  const statisticalByMonth = useSelector((state) => state.statisticalAdmin.statisticalByMonth)
  const statisticalByDay = useSelector((state) => state.statisticalAdmin.statisticalByDay)
  const statisticalByYears = useSelector((state) => state.statisticalAdmin.statisticalByYears)
  const statisticalByTop5 = useSelector((state) => state.statisticalAdmin.statisticalByTop5)

  const booking = useSelector((state) => state.booking.bookings)
  const userDetail = JSON.parse(localStorage.getItem('ownerDetail'));
  const UserID = userDetail?.data.id;
  const currentDate = new Date();
  // Lấy thông tin về tháng và năm từ ngày hiện tại
  const currentMonth = currentDate.getMonth() + 1; // Tháng bắt đầu từ 0
  const currentYear = new Date().getFullYear();
  const currentDateTime = new Date().getDate();
  const [homestayname, setHomestayName] = useState('')
  const [namebooking, setNameBooking] = useState('')
  const [valueselect, setValueSelect] = useState('1')
  const [year, setYear] = useState(new Date().getFullYear())
  const onChange = (dateString) => {
    setYear(dateString)
    dispatch(fetchStatisticalByYear(UserID, dateString))
  };
  useEffect(() => {
    dispatch(fetchStatisticalByYear(year))
    dispatch(fetchStatisticalByYears(year))
    dispatch(fetchStatisticalByDay(currentDateTime, currentMonth, currentYear))
    dispatch(fetchStatisticalByMonth(currentMonth, currentYear))
    dispatch(fetchStatisticalByTop5(year))
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
      title: 'Tên Homestay',
      dataIndex: 'name',
      key: 'name'
    },
    {
      title: 'Số phòng',
      dataIndex: 'roomNumber',
      key: 'roomNumber'
    },
    {
      title: 'Doanh số',
      dataIndex: 'doanhSo',
      key: 'doanhSo'
    },
    {
      title: 'Tổng số tiền',
      dataIndex: 'tongSoTien',
      key: 'tongSoTien',
      render(str) {
        return str + 'VNĐ'
      }
    },
    {
      title: 'Địa chỉ homestay',
      dataIndex: 'address',
      key: 'address'
    }
  ];

  return (
    <div>
      <Title style={{ marginTop: '20px' }}>Thống kê doanh thu</Title>
      <div>
        <div style={{ display: 'flex', justifyContent: 'center' }}>
          <div style={{ width: '25%', height: 180, backgroundColor: '#321fdb', padding: 25, marginRight: 60, borderRadius: 8 }}>
            <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={3}> {statisticalByDay.tongSoTien == null ? 0 : statisticalByDay.tongSoTien} VNĐ</Title>
            <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={5}> Doanh thu ngày hôm nay</Title>
            <img src={imgchart} style={{ width: '100%' }} />
          </div>
          <div style={{ width: '25%', height: 180, backgroundColor: '#3399ff', padding: 25, marginRight: 60, borderRadius: 8 }}>
            <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={3}> {statisticalByMonth.tongSoTien == null ? 0 : statisticalByMonth.tongSoTien} VNĐ</Title>
            <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={5}> Doanh thu tháng này </Title>
            <img src={imgBluechart} style={{ width: '100%' }} />
          </div>
          <div style={{ width: '25%', height: 180, backgroundColor: '#f9b115', padding: 25, marginRight: 60, borderRadius: 8 }}>
            <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={3}> {statisticalByYears.tongSoTien == null ? 0 : statisticalByYears.tongSoTien} VNĐ</Title>
            <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={5}> Doanh thu năm nay </Title>
            <img src={imgyellowchart} style={{ width: '100%', marginTop: 10 }} />
          </div>
          <div style={{ width: '25%', height: 180, backgroundColor: '#e55353', padding: 25, borderRadius: 8 }}>
            <Title style={{ marginTop: '1px', color: 'white', fontWeight: 600 }} level={3}> {statisticalByYears.doanhSo == null ? 0 : statisticalByYears.doanhSo} lượt</Title>
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
        <Title level={3}>Top 5 homestay có doanh thu cao nhất</Title>
        <Table columns={columns} dataSource={statisticalByTop5} />
      </div>
    </div>
  )
}
export default Statistical