import { test, expect } from '@playwright/test';

test('Login link to Facebook from Webtoon', async ({ page }) => {
  await page.goto('https://www.webtoons.com/en/');

  // Expect a title "to contain" a substring.
  await page.getByRole('button',{name: 'Log in'}).click();
  await page.getByRole('link',{name:'Continue with Facebook'}).click();
  await expect(page).toHaveTitle('Log into Facebook')

  const info = await page.getByRole('link',{name:'Create'}).first().textContent()
  console.log("Message is:"+info)

  // await page.getByRole('textbox',{name:'email'}).fill('')
  // await page.getByRole('textbox',{name:'pass'}).fill('')
  // await page.getByRole('button',{name:'Log In'}).click()

  // await page.getByRole('button',{name:'Continue As Eric'}).click()
  // await expect(page.getByRole('button',{name:'Eric Tong'})).toBeVisible();

});

// test('get started link', async ({ page }) => {
//   await page.goto('https://playwright.dev/');

//   // Click the get started link.
//   await page.getByRole('link', { name: 'Get started' }).click();

//   // Expects page to have a heading with the name of Installation.
//   await expect(page.getByRole('heading', { name: 'Installation' })).toBeVisible();
// });
