require 'sinatra'

get '/calendars' do
  File.read('calendars.json')
end

get '/calendars/1/events' do
  File.read('events.json')
end

get '/users' do
  File.read('users.json')
end

post '/calendars/*/events' do
  File.read('event.json')
end

require 'socket'
ip=Socket.ip_address_list.detect{|intf| intf.ipv4_private?}
ip.ip_address if ip
puts "ip #{ip.ip_address}"