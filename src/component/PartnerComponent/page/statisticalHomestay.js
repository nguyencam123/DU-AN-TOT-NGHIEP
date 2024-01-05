import { Button, DatePicker, Table, Tabs, Typography } from 'antd'
import { Column } from '@ant-design/plots'
import { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import moment from 'moment'
import {
  fetchStatisticalByDay,
  fetchStatisticalByMonth,
  fetchStatisticalByTop5,
  fetchStatisticalByYear,
  fetchStatisticalByYears,
} from '../../../features/owner_homestay/statistical/statisticalThunk'
import imgchart from '../../../assets/img/Tochartstatical.png'
import imgyellowchart from '../../../assets/img/yellowtoChart.png'
import imgBluechart from '../../../assets/img/blueimg.png'
import imgRedchart from '../../../assets/img/redimg.png'
import dayjs from 'dayjs'
import {
  fetchBooking,
  getBookingByNameHomestay,
} from '../../../features/owner_homestay/getbooking/bookingThunk'
import * as XLSX from 'xlsx'

const { Title } = Typography
const StatisticalHomestay = () => {
  const dispatch = useDispatch()
  const statistical = useSelector(
    (state) => state.statistical.statisticalByYear,
  )
  const statisticalByMonth = useSelector(
    (state) => state.statistical.statisticalByMonth,
  )
  const statisticalByDay = useSelector(
    (state) => state.statistical.statisticalByDay,
  )
  const statisticalByYears = useSelector(
    (state) => state.statistical.statisticalByYears,
  )
  const statisticalByTop5 = useSelector(
    (state) => state.statistical.statisticalByTop5,
  )

  const booking = useSelector((state) => state.booking.bookings)
  const userDetail = JSON.parse(localStorage.getItem('ownerDetail'))
  const UserID = userDetail?.data.id
  const currentDate = new Date()
  // Lấy thông tin về tháng và năm từ ngày hiện tại
  const currentMonth = currentDate.getMonth() + 1 // Tháng bắt đầu từ 0
  const currentYear = new Date().getFullYear()
  const currentDateTime = new Date().getDate()
  const [homestayname, setHomestayName] = useState('')
  const [namebooking, setNameBooking] = useState('')
  const [valueselect, setValueSelect] = useState('1')
  const [year, setYear] = useState(currentYear)

  const onChange = (dateString) => {
    setYear(new Date(dateString).getFullYear())
    dispatch(fetchStatisticalByYear(UserID, dateString))
    dispatch(fetchStatisticalByTop5(UserID, dateString))
  }
  useEffect(() => {
    dispatch(fetchStatisticalByYear(UserID, year))
    dispatch(fetchStatisticalByYears(UserID, year))
    dispatch(
      fetchStatisticalByDay(UserID, currentDateTime, currentMonth, currentYear),
    )
    dispatch(fetchStatisticalByMonth(UserID, currentMonth, currentYear))
    dispatch(
      getBookingByNameHomestay(UserID, homestayname, namebooking, valueselect),
    )
  }, [])

  const convertDataForChart = (data) => {
    if (!data || data.length === 0) {
      return []
    }

    // Chuyển đổi dữ liệu thành định dạng phù hợp cho biểu đồ
    return data.map((item, index) => ({
      date: getMonthName(index + 1), // Hàm này sẽ cần được định nghĩa để trả về tên tháng dựa trên số thứ tự
      doanhSo: item.doanhSo,
      tongSoTien: item.tongSoTien,
      type: 'register', // Set a default type or determine it based on your requirements
    }))
  }

  // Hàm để lấy tên tháng dựa trên số thứ tự (1-12)
  const getMonthName = (monthNumber) => {
    const monthNames = [
      'Tháng 1',
      'Tháng 2',
      'Tháng 3',
      'Tháng 4',
      'Tháng 5',
      'Tháng 6',
      'Tháng 7',
      'Tháng 8',
      'Tháng 9',
      'Tháng 10',
      'Tháng 11',
      'Tháng 12',
    ]

    return monthNames[monthNumber - 1]
  }

  const formatCurrency = (value) => {
    // Sử dụng Intl.NumberFormat để định dạng giá trị tiền tệ
    return new Intl.NumberFormat('vi-VN', {
      style: 'currency',
      currency: 'VND',
    }).format(value)
  }
  const convertedData = convertDataForChart(statistical)
  const formattedData = convertedData.map((item) => ({
    date: item.date,
    tongSoTien: parseInt(item.tongSoTien),
  }))

  const config = {
    data: formattedData,
    xField: 'date',
    yField: 'tongSoTien',
    label: {
      position: 'middle',
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
    yAxis: {
      label: {
        formatter: (value) => formatCurrency(value),
      },
    },
    tooltip: {
      customContent: (title, items) => {
        const formattedItems = items.map((item) => ({
          name: item.name,
          value: formatCurrency(item.value),
        }))

        return `
                    <div class="g2-tooltip">
                        <div class="g2-tooltip-title">${title}</div>
                        <ul class="g2-tooltip-list">
                            ${formattedItems
                              .map(
                                (item) =>
                                  `<li class="g2-tooltip-list-item">${item.name}: ${item.value}</li>`,
                              )
                              .join('')}
                        </ul>
                    </div>
                `
      },
    },
    meta: {
      date: {
        alias: 'Ngày',
      },
      tongSoTien: {
        alias: 'Tổng Số Tiền',
      },
    },
  }

  // table
  const columns = [
    {
      title: 'Tên Homestay',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: 'Số phòng',
      dataIndex: 'roomNumber',
      key: 'roomNumber',
    },
    {
      title: 'Doanh số',
      dataIndex: 'doanhSo',
      key: 'doanhSo',
    },
    {
      title: 'Tổng số tiền',
      dataIndex: 'tongSoTien',
      key: 'tongSoTien',
      render(str) {
        return formatCurrency(str)
      },
    },
    {
      title: 'Địa chỉ homestay',
      dataIndex: 'address',
      key: 'address',
    },
  ]
  /**
   * xuất file excel
   */

  const importAndAppendData = (data) => {
    const today = moment().format('YYYY-MM-DD')
    // Sample data
    const title = [`Thống kê lượt đặt phòng trong năm(ngày xuất file ${today})`]
    const header = ['Name', 'Age', 'City']
    // const data = [
    //   { name: 'John Doe', age: 25, city: 'New York' },
    //   { name: 'Jane Smith', age: 30, city: 'San Francisco' },
    //   { name: 'Bob Johnson', age: 28, city: 'Los Angeles' },
    // ];
    const headers = Object.keys(data[0])
    const values = data.map((obj) => headers.map((key) => obj[key]))

    // Create a worksheet
    const ws = XLSX.utils.aoa_to_sheet([title, [], [], [...headers], ...values])

    // Apply styles to title
    const titleStyle = {
      font: { bold: true, size: 30, color: { rgb: 'FF0000' } }, // You can adjust the size and color as needed
      alignment: { horizontal: 'center' },
    }
    const titleCell = ws[XLSX.utils.encode_cell({ c: 0, r: 0 })] // Assuming the title is in cell A1
    XLSX.utils.format_cell(titleCell, titleStyle)

    // Apply styles to headers
    const headerStyle = {
      font: { bold: true },
      border: {
        top: { style: 'thin' },
        bottom: { style: 'thin' },
        left: { style: 'thin' },
        right: { style: 'thin' },
      },
    }
    headers.forEach((header, index) => {
      const cell_address = { c: index, r: 3 } // assuming headers start at row 3
      XLSX.utils.format_cell(ws[cell_address], headerStyle)
    })

    // Apply styles to data cells
    const dataStyle = {
      border: {
        top: { style: 'thin' },
        bottom: { style: 'thin' },
        left: { style: 'thin' },
        right: { style: 'thin' },
      },
    }
    values.forEach((row, rowIndex) => {
      row.forEach((cell, cellIndex) => {
        const cell_address = { c: cellIndex, r: rowIndex + 4 } // assuming data starts at row 4
        XLSX.utils.format_cell(ws[cell_address], dataStyle)
      })
    })

    // Create a workbook
    const wb = XLSX.utils.book_new()
    XLSX.utils.book_append_sheet(wb, ws, 'Sheet1')
    // Save the workbook to a file
    XLSX.writeFile(wb, `Thống_kê_doanh_thu_TravelViVu_${today}.xlsx`)
  }

  // Example usage
  const exportExcel = () => {
    importAndAppendData(statisticalByTop5)
  }
  return (
    <div>
      <Title style={{ marginTop: '20px' }}>Thống kê doanh thu</Title>
      <div>
        <div style={{ display: 'flex', justifyContent: 'center' }}>
          <div
            style={{
              width: '25%',
              height: '100%',
              backgroundColor: '#321fdb',
              padding: 25,
              marginRight: 60,
              borderRadius: 8,
            }}
          >
            <Title
              style={{ marginTop: '1px', color: 'white', fontWeight: 600 }}
              level={3}
            >
              {' '}
              {statisticalByDay.tongSoTien == null
                ? 0
                : formatCurrency(statisticalByDay.tongSoTien)}{' '}
            </Title>
            <Title
              style={{ marginTop: '1px', color: 'white', fontWeight: 600 }}
              level={5}
            >
              {' '}
              Doanh thu ngày hôm nay
            </Title>
            <img src={imgchart} style={{ width: '100%' }} />
          </div>
          <div
            style={{
              width: '25%',
              height: '100%',
              backgroundColor: '#3399ff',
              padding: 25,
              marginRight: 60,
              borderRadius: 8,
            }}
          >
            <Title
              style={{ marginTop: '1px', color: 'white', fontWeight: 600 }}
              level={3}
            >
              {' '}
              {statisticalByMonth.tongSoTien == null
                ? 0
                : formatCurrency(statisticalByMonth.tongSoTien)}{' '}
            </Title>
            <Title
              style={{ marginTop: '1px', color: 'white', fontWeight: 600 }}
              level={5}
            >
              {' '}
              Doanh thu tháng này{' '}
            </Title>
            <img src={imgBluechart} style={{ width: '100%' }} />
          </div>
          <div
            style={{
              width: '25%',
              height: '100%',
              backgroundColor: '#f9b115',
              padding: 25,
              marginRight: 60,
              borderRadius: 8,
            }}
          >
            <Title
              style={{ marginTop: '1px', color: 'white', fontWeight: 600 }}
              level={3}
            >
              {' '}
              {statisticalByYears?.tongSoTien == null
                ? 0
                : formatCurrency(statisticalByYears?.tongSoTien)}{' '}
            </Title>
            <Title
              style={{ marginTop: '1px', color: 'white', fontWeight: 600 }}
              level={5}
            >
              {' '}
              Doanh thu năm nay{' '}
            </Title>
            <img
              src={imgyellowchart}
              style={{ width: '100%', marginTop: 10 }}
            />
          </div>
          <div
            style={{
              width: '25%',
              height: '100%',
              backgroundColor: '#e55353',
              padding: 25,
              borderRadius: 8,
            }}
          >
            <Title
              style={{ marginTop: '1px', color: 'white', fontWeight: 600 }}
              level={3}
            >
              {' '}
              {statisticalByYears?.doanhSo == null
                ? 0
                : statisticalByYears?.doanhSo}{' '}
              lượt
            </Title>
            <Title
              style={{ marginTop: '1px', color: 'white', fontWeight: 600 }}
              level={5}
            >
              Số lượt đã đặt phòng trong năm
            </Title>
            <img src={imgRedchart} style={{ width: '100%', marginTop: 1 }} />
          </div>
        </div>
      </div>
      <div style={{ display: 'flex', marginTop: '20px' }}>
        <Title level={5} style={{ marginTop: '20px' }}>
          Biểu đồ thống kê doanh thu trong năm {year}
        </Title>
        <DatePicker
          onChange={onChange}
          picker='year'
          style={{
            marginLeft: 'auto',
            width: 300,
            height: 40,
            marginTop: '20px',
          }}
          disabledDate={(current) => current && current.year() > currentYear}
          defaultValue={dayjs(`${currentYear}-01-01`)}
        />
      </div>
      <Column {...config} />
      <div style={{ marginTop: '40px' }}>
        <div style={{ display: 'flex', marginBottom: 20 }}>
          <Title level={5}>
            Top 5 homestay có doanh thu cao nhất trong năm {year}
          </Title>

          <Button
            style={{ marginLeft: 'auto' }}
            type='primary'
            onClick={exportExcel}
          >
            Xuất file thống kê đặt phòng
          </Button>
        </div>
        <Table columns={columns} dataSource={statisticalByTop5} />
      </div>
    </div>
  )
}
export default StatisticalHomestay
