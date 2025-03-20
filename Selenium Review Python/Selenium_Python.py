from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.action_chains import ActionChains

driver = webdriver.Firefox()
driver.get("https://google.com")

search_field = driver.find_element(By.TAG_NAME,"textarea")
search_field2 = driver.find_element(By.ID,"APjFqb")

action = ActionChains(driver)
action.click(search_field2)
action.send_keys("This is a search entry")

action.perform()
